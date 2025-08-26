import { Component } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import PlayersList from "./components/PlayersList";
import CreatePlayer from "./components/CreatePlayer";
import PlayerUpdate from "./components/PlayerUpdate";
import { withNavigation } from "./common/withNavigation";

const PlayerUpdateWithNav = withNavigation(PlayerUpdate);


class App extends Component {

  render() {
    return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<PlayersList />} />
          <Route path="/players" element={<PlayersList />} />
          <Route path="/add-player" element={<CreatePlayer />} />
          <Route path="/update-player/:id" element={<PlayerUpdate />} />
        </Routes>

      </BrowserRouter>
    );
  }
}

export default App;