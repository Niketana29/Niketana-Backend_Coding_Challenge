import { Component } from "react";
import playerApi from "../services/playerApi";
import { Link } from "react-router-dom";
import { withNavigation } from "../common/withNavigation";


class PlayersList extends Component {

    constructor(props) {

        super(props);
        this.state = {
            players: [],
            /*Search by jersey number*/
            searchJersey: "",
            searchResult: null,
            searchError: ""
        };
    }

    componentDidMount() {
        playerApi.getAllPlayers()
            .then(response => {
                this.setState({ players: response.data });
            });
    }

    goToCreate = () => {
        this.props.navigation.navigate("/add-player");
    };

    goToUpdate = (id) => {
        this.props.navigation.navigate(`/update-player/${id}`);
    };

    /*Search By Jersey Number*/

    handleSearch = () => {
        const { searchJersey } = this.state;

        if (!searchJersey) {
            this.setState({
                searchResult: null,
                searchError: "Please enter a valid jersey number"
            });
            return;
        }

        playerApi.findByJerseyNumber(Number(searchJersey))
            .then(response => {
                this.setState({ searchResult: response.data, searchError: "" });
            })
            .catch(err => {
                this.setState({
                    searchResult: null,
                    searchError: "Player with the mentioned jersey number is not found"
                });
            });
    };

    render() {
        const { players, searchJersey, searchResult, searchError } = this.state;

        return (

            <div className="container mt-4">
                <h2>Cricket Team Players</h2>

                { /*Search By Jersey Number*/}

                <div className="mb-3 d-flex align-items-center gap-2">
                    <input
                        type="number"
                        className="form-control"
                        placeholder="Enter Jersey Number"
                        value={searchJersey}
                        onChange={(e) => this.setState({ searchJersey: e.target.value })}
                    />
                    <button className="btn btn-info" onClick={this.handleSearch}>Search</button>
                </div>


                {searchResult && (
                    <div className="alert alert-success">
                        <p><strong>Player ID:</strong> {searchResult.playerId}</p>
                        <p><strong>Player Name:</strong> {searchResult.playerName}</p>
                        <p><strong>Jersey Number:</strong> {searchResult.jerseyNumber}</p>
                        <p><strong>Role:</strong> {searchResult.role}</p>
                    </div>
                )}

                {searchError && (
                    <div className="alert alert-danger">{searchError}</div>
                )}

                <button
                    className="btn btn-primary mb-3"
                    onClick={this.goToCreate}
                >
                    Add Player
                </button>

                <table className="table table-bordered">
                    <thead>
                        <tr>
                            <th>Player ID</th>
                            <th>Player Name</th>
                            <th>Jersey Number</th>
                            <th>Role</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.players.map((player) => (
                            <tr key={player.playerId}>
                                <td>{player.playerId}</td>
                                <td>{player.playerName}</td>
                                <td>{player.jerseyNumber}</td>
                                <td>{player.role}</td>
                                <td>
                                    <button
                                        className="btn btn-warning"
                                        onClick={() => this.goToUpdate(player.playerId)}
                                    >
                                        Update
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default withNavigation(PlayersList);