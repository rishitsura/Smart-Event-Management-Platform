import React, { useEffect, useState } from 'react';
import {
  Container,
  Typography,
  Grid,
  Box,
  CircularProgress,
  Alert,
} from '@mui/material';
import EventCard from '../components/EventCard';
import { Event } from '../types/Event';
import api from '../services/api';

const HomePage: React.FC = () => {
  const [events, setEvents] = useState<Event[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetchEvents();
  }, []);

  const fetchEvents = async () => {
    try {
      setLoading(true);
      const response = await api.get('/api/events/public');
      setEvents(response.data.content);
    } catch (err) {
      setError('Failed to fetch events');
      console.error('Error fetching events:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleRSVP = async (eventId: number) => {
    try {
      await api.post(`/api/rsvp`, {
        eventId,
        status: 'ATTENDING',
      });
      // Refresh events to update capacity
      fetchEvents();
    } catch (err) {
      console.error('Error submitting RSVP:', err);
    }
  };

  if (loading) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="50vh">
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return (
      <Container maxWidth="lg" sx={{ mt: 4 }}>
        <Alert severity="error">{error}</Alert>
      </Container>
    );
  }

  return (
    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
      <Typography variant="h3" component="h1" gutterBottom align="center">
        Upcoming Events
      </Typography>
      
      <Typography variant="h6" color="text.secondary" align="center" paragraph>
        Discover and join amazing events in your community
      </Typography>

      {events.length === 0 ? (
        <Box textAlign="center" mt={4}>
          <Typography variant="body1">No events available at the moment.</Typography>
        </Box>
      ) : (
        <Grid container spacing={3} justifyContent="center">
          {events.map((event) => (
            <Grid item xs={12} sm={6} md={4} key={event.id}>
              <EventCard event={event} onRSVP={handleRSVP} />
            </Grid>
          ))}
        </Grid>
      )}
    </Container>
  );
};

export default HomePage;
