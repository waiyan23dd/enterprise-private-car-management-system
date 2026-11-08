import VehicleTrackingMapDemo from "./components/testLocation/VehicleTrackingMapDemo";

const App = () => {
    const driverId = 1;

    return (
        <div className="container mt-4">
            <h3>Driver GPS Tracking Test</h3>

            <p>
                <strong>Driver ID:</strong> {driverId}
            </p>

            <VehicleTrackingMapDemo />
        </div>
    );
};

export default App;