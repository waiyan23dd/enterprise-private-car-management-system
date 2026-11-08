import { useEffect, useRef, useState } from 'react';
import axios from 'axios';

function DriverGPSTracking({ vehicleId }) {
    const [tracking, setTracking] = useState(false);
    const [location, setLocation] = useState(null);
    const [error, setError] = useState('');

    const watchId = useRef(null);
    const lastSent = useRef(0);

    const startTracking = () => {
        if (!navigator.geolocation) {
            setError('GPS is not supported by this device.');
            return;
        }

        setError('');

        watchId.current = navigator.geolocation.watchPosition(
            async (position) => {
                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;

                setLocation({ latitude, longitude });

                const now = Date.now();

                if (now - lastSent.current < 5000) return;

                lastSent.current = now;

                try {
                    await axios.post(
                        'http://localhost:8080/api/vehicle-locations',
                        {
                            vehicleId,
                            latitude,
                            longitude,
                            accuracy: position.coords.accuracy
                        },
                        {
                            headers: {
                                Authorization: `Bearer ${localStorage.getItem('accessToken')}`
                            }
                        }
                    );
                } catch (err) {
                    console.error('Location update failed:', err);
                }
            },
            (err) => {
                setError(err.message);
            },
            {
                enableHighAccuracy: true,
                maximumAge: 0,
                timeout: 15000
            }
        );

        setTracking(true);
    };

    const stopTracking = () => {
        if (watchId.current !== null) {
            navigator.geolocation.clearWatch(watchId.current);
            watchId.current = null;
        }

        setTracking(false);
    };

    useEffect(() => {
        return () => {
            if (watchId.current !== null) {
                navigator.geolocation.clearWatch(watchId.current);
            }
        };
    }, []);

    return (
        <div className="container mt-4">
            <h4>Driver GPS Tracking</h4>

            <p>
                Status: {tracking ? 'Tracking Active' : 'Tracking Stopped'}
            </p>

            {location && (
                <div>
                    <p>Latitude: {location.latitude}</p>
                    <p>Longitude: {location.longitude}</p>
                </div>
            )}

            {error && <p className="text-danger">{error}</p>}

            {!tracking ? (
                <button className="btn btn-success" onClick={startTracking}>
                    Start GPS Tracking
                </button>
            ) : (
                <button className="btn btn-danger" onClick={stopTracking}>
                    Stop GPS Tracking
                </button>
            )}
        </div>
    );
}

export default DriverGPSTracking;