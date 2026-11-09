import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';

function LocationMap() {
    const position = [21.9588, 96.0891];

    return (
        <MapContainer
            center={position}
            zoom={13}
            style={{ height: '400px', width: '100%' }}
        >
            <TileLayer
                attribution='&copy; OpenStreetMap contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />

            <Marker position={position}>
                <Popup>Mandalay</Popup>
            </Marker>
        </MapContainer>
    );
}

export default LocationMap;