import { useEffect, useState } from 'react';
import axios from 'axios';

import {
    MapContainer,
    TileLayer,
    Marker,
    Popup,
    useMap
} from 'react-leaflet';

import L from 'leaflet';

import 'leaflet/dist/leaflet.css';

const vehicleIcon = L.divIcon({
    className: 'vehicle-marker',
    html: '<i class="fa-solid fa-car-side"></i>',
    iconSize: [40, 40],
    iconAnchor: [20, 20]
});

function AnimatedVehicleMarker({ targetPosition }) {
    const [position, setPosition] = useState(targetPosition);
    const map = useMap();

    useEffect(() => {
        if (!targetPosition) return;

        const start = position;
        const end = targetPosition;

        const duration = 2500;
        const startTime = performance.now();

        let animationFrame;

        const animate = (time) => {
            const progress = Math.min(
                (time - startTime) / duration,
                1
            );

            const latitude =
                start[0] + (end[0] - start[0]) * progress;

            const longitude =
                start[1] + (end[1] - start[1]) * progress;

            setPosition([latitude, longitude]);

            if (progress < 1) {
                animationFrame = requestAnimationFrame(animate);
            }
        };

        animationFrame = requestAnimationFrame(animate);

        return () => cancelAnimationFrame(animationFrame);
    }, [targetPosition]);

    useEffect(() => {
        if (position) {
            map.panTo(position);
        }
    }, [position, map]);

    return (
        <Marker position={position} icon={vehicleIcon}>
            <Popup>Driver's Current Location</Popup>
        </Marker>
    );
}

function VehicleTrackingMap({ driverId }) {
    const [position, setPosition] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const token = localStorage.getItem('accessToken');

        const fetchLocation = async () => {
            try {
                const response = await axios.get(
                    `http://localhost:8080/api/vehicle-locations/vehicles/${driverId}/latest`,
                    {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    }
                );

                const data = response.data;

                if (
                    typeof data.latitude === 'number' &&
                    typeof data.longitude === 'number'
                ) {
                    setPosition([
                        data.latitude,
                        data.longitude
                    ]);
                }

                setLoading(false);
            } catch (error) {
                console.error('Unable to get vehicle location:', error);
                setLoading(false);
            }
        };

        fetchLocation();

        const interval = setInterval(fetchLocation, 3000);

        return () => clearInterval(interval);
    }, [driverId]);

    if (loading) {
        return <p>Loading vehicle location...</p>;
    }

    if (!position) {
        return <p>Vehicle location is not available.</p>;
    }

    return (
        <div>
            <h4>Live Vehicle Tracking</h4>

            <MapContainer
                center={position}
                zoom={14}
                style={{ height: '450px', width: '100%' }}
            >
                <TileLayer
                    attribution='&copy; OpenStreetMap contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />

                <AnimatedVehicleMarker targetPosition={position} />
            </MapContainer>
        </div>
    );
}

export default VehicleTrackingMap;