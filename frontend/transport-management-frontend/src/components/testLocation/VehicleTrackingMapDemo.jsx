import { useEffect, useState } from 'react';
import {
    MapContainer,
    TileLayer,
    Marker,
    Popup,
    Polyline
} from 'react-leaflet';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import './VehicleTrackingMap.css';

const route = [
    [16.8661, 96.1951],
    [16.8680, 96.1980],
    [16.8700, 96.2010],
    [16.8720, 96.2040],
    [16.8740, 96.2070],
    [16.8760, 96.2100],
    [16.8780, 96.2130]
];

const vehicleIcon = L.divIcon({
    className: 'vehicle-marker',
    html: '<i class="fa-solid fa-car-side"></i>',
    iconSize: [35, 35],
    iconAnchor: [17, 17]
});

function VehicleTrackingMapDemo() {
    const [positionIndex, setPositionIndex] = useState(0);
    const [isMoving, setIsMoving] = useState(true);

    const currentPosition = route[positionIndex];

    useEffect(() => {
        if (!isMoving) return;

        const interval = setInterval(() => {
            setPositionIndex(prev => {
                if (prev >= route.length - 1) {
                    setIsMoving(false);
                    return prev;
                }

                return prev + 1;
            });
        }, 2000);

        return () => clearInterval(interval);
    }, [isMoving]);

    const restartSimulation = () => {
        setPositionIndex(0);
        setIsMoving(true);
    };

    return (
        <div>
            <div className="d-flex gap-2 mb-3">
                <button
                    className="btn btn-success"
                    onClick={() => setIsMoving(true)}
                    disabled={isMoving || positionIndex === route.length - 1}
                >
                    Start
                </button>

                <button
                    className="btn btn-warning"
                    onClick={() => setIsMoving(false)}
                >
                    Pause
                </button>

                <button
                    className="btn btn-primary"
                    onClick={restartSimulation}
                >
                    Restart
                </button>
            </div>

            <div className="mb-2">
                <strong>Vehicle Status:</strong>{' '}
                {isMoving ? 'Moving' : 'Stopped'}
            </div>

            <MapContainer
                center={route[0]}
                zoom={15}
                style={{ height: '500px', width: '100%' }}
            >
                <TileLayer
                    attribution='&copy; OpenStreetMap contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />

                <Polyline positions={route} />

                <Marker
                    position={currentPosition}
                    icon={vehicleIcon}
                >
                    <Popup>
                        <strong>Test Vehicle</strong>
                        <br />
                        Status: {isMoving ? 'Moving' : 'Stopped'}
                        <br />
                        GPS: {currentPosition[0]}, {currentPosition[1]}
                    </Popup>
                </Marker>
            </MapContainer>
        </div>
    );
}

export default VehicleTrackingMapDemo;