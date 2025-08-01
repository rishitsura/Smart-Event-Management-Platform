import React, { useEffect, useState } from 'react';
import {
  Card,
  CardContent,
  CardActions,
  Typography,
  Button,
  Chip,
  Box,
} from '@mui/material';
import {
  LocationOn,
  Schedule,
  People,
  AttachMoney,
} from '@mui/icons-material';
import { Event } from '../types/Event';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

interface EventCardProps {
  event: Event;
  onRSVP?: (eventId: number) => void;
}

const EventCard: React.FC<EventCardProps> = ({ event, onRSVP }) => {
  const [attendeeCount, setAttendeeCount] = useState<number>(0);

  useEffect(() => {
    // WebSocket connection for real-time capacity updates
    const socket = new SockJS('http://localhost:8080/ws');
    const stompClient = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    stompClient.onConnect = () => {
      stompClient.subscribe(`/topic/capacity/${event.id}`, (message) => {
        const capacityData = JSON.parse(message.body);
        setAttendeeCount(capacityData.attendeeCount);
      });
    };

    stompClient.activate();

    return () => {
      stompClient.deactivate();
    };
  }, [event.id]);

  const formatDate = (dateString: string) => {
    return new Date(dateString).toLocaleDateString('en-US', {
      weekday: 'short',
      year: 'numeric',
      month: 'short',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
    });
  };

  const spotsLeft = event.capacity - attendeeCount;

  return (
    <Card sx={{ maxWidth: 400, margin: 2, height: '100%', display: 'flex', flexDirection: 'column' }}>
      <CardContent sx={{ flexGrow: 1 }}>
        <Box display="flex" justifyContent="space-between" alignItems="flex-start" mb={2}>
          <Typography variant="h6" component="h2" gutterBottom>
            {event.title}
          </Typography>
          <Chip
            label={event.status}
            color={event.status === 'PUBLISHED' ? 'success' : 'default'}
            size="small"
          />
        </Box>

        <Typography variant="body2" color="text.secondary" paragraph>
          {event.description}
        </Typography>

        <Box display="flex" alignItems="center" mb={1}>
          <Schedule fontSize="small" sx={{ mr: 1, color: 'text.secondary' }} />
          <Typography variant="body2" color="text.secondary">
            {formatDate(event.startDate)}
          </Typography>
        </Box>

        <Box display="flex" alignItems="center" mb={1}>
          <LocationOn fontSize="small" sx={{ mr: 1, color: 'text.secondary' }} />
          <Typography variant="body2" color="text.secondary">
            {event.location}
          </Typography>
        </Box>

        <Box display="flex" alignItems="center" mb={1}>
          <People fontSize="small" sx={{ mr: 1, color: 'text.secondary' }} />
          <Typography variant="body2" color="text.secondary">
            {spotsLeft} spots left ({attendeeCount}/{event.capacity})
          </Typography>
        </Box>

        <Box display="flex" alignItems="center">
          <AttachMoney fontSize="small" sx={{ mr: 1, color: 'text.secondary' }} />
          <Typography variant="body2" color="text.secondary">
            ${event.price}
          </Typography>
        </Box>
      </CardContent>

      <CardActions>
        {onRSVP && (
          <Button
            size="small"
            variant="contained"
            onClick={() => onRSVP(event.id)}
            disabled={spotsLeft <= 0}
          >
            {spotsLeft <= 0 ? 'Sold Out' : 'RSVP'}
          </Button>
        )}
        <Button size="small">
          Learn More
        </Button>
      </CardActions>
    </Card>
  );
};

export default EventCard;
