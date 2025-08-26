import httpClient from "../api/httpClient";

class PlayerApi {
    getAllPlayers() {
      return httpClient.get("/players/getall");
    }
  
    createPlayer(player) {
      return httpClient.post("/players/insert", player);
    }
  
    getPlayerById(id) {
      return httpClient.get(`/players/getbyid/${id}`);
    }
  
    updatePlayer(player) {
      return httpClient.put("/players/update", player);
    }

    findByJerseyNumber(jerseyNumber) {
        return httpClient.get(`/players/getbyjersey/${jerseyNumber}`);
    }
  
  }
  
  export default new PlayerApi();
