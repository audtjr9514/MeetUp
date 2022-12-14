import './App.css';

import { Routes, Route } from 'react-router-dom';

import Admin from './pages/Admin';
import AdminLogin from './pages/AdminLogin';
import CreateMeetup from './pages/CreateMeetup';
import EditMeetup from './pages/EditMeetup';
import Login from './pages/Login';
import MeetUp from './pages/MeetUp';
import NotFound from './pages/NotFound';
import Tutorial from './pages/Tutorial';
import Settings from './pages/Settings';
import AdminSignup from './pages/AdminSignup';
import PrivateRoutes from './components/auth/privateRoutes';
import RouteChangeTracker from './components/RouteChangeTracker';
import CreateChannel from './pages/CreateChannel';

function App() {
  RouteChangeTracker();

  return (
    <div className="font-pre">
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/*" element={<NotFound />} />
        <Route path="/admin-meetup2022" element={<Admin />} />
        <Route path="/admin-login" element={<AdminLogin />} />
        <Route path="/admin-signup" element={<AdminSignup />} />
        <Route element={<PrivateRoutes />}>
          <Route path="/calendar/:userId" element={<MeetUp />} />
          <Route path="/tutorial" element={<Tutorial />} />
          <Route path="/create-meetup" element={<CreateMeetup />} />
          <Route path="/edit-meetup/:id" element={<EditMeetup />} />
          <Route path="/create-channel" element={<CreateChannel />} />
          <Route path="/settings" element={<Settings />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
