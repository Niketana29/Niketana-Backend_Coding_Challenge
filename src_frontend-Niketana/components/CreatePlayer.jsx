import { Component } from "react";
import playerApi from "../services/playerApi";
import { withNavigation } from "../common/withNavigation";


class CreatePlayer extends Component {

    constructor(props) {

        super(props);
        this.state = {
            playerId: "",
            playerName: "",
            jerseyNumber: "",
            role: "",
            errors: {}
        };
    }

    handleChange = (e) => {

        const { name, value } = e.target;
        this.setState({ [name]: value }, () => {
            this.validateField(name, value);
        });
    };

    validateField = (name, value) => {
        const errors = { ...this.state.errors };
        switch (name) {
            case "playerName":
                if (!value) errors.playerName = "Player Name is required";
                else if (value.length < 3 || value.length > 50)
                    errors.playerName = "Player Name must be 3-50 characters";
                else delete errors.playerName;
                break;
            case "jerseyNumber":
                if (!value) errors.jerseyNumber = "Jersey Number is required";
                else if (value < 1 || value > 50)
                    errors.jerseyNumber = "Jersey Number must be between 1 and 50";
                else delete errors.jerseyNumber;
                break;
            case "role":
                const validRoles = ["Batsman", "Bowler", "Keeper", "All Rounder"];
                if (!value) errors.role = "Role is required";
                else if (!validRoles.includes(value))
                    errors.role = `Role must be one of: ${validRoles.join(", ")}`;
                else delete errors.role;
                break;
            default:
                break;
        }
        this.setState({ errors });
    };

    handleSubmit = (e) => {
        e.preventDefault();
        const { playerName, jerseyNumber, role, errors } = this.state;

        this.validateField("playerName", playerName);
        this.validateField("jerseyNumber", jerseyNumber);
        this.validateField("role", role);

        if (Object.keys(this.state.errors).length > 0) {
            alert("Please fix validation errors before submitting!");
            return;
        }

        const player = { playerName, jerseyNumber: Number(jerseyNumber), role };
        playerApi.createPlayer(player)
            .then(() => this.props.navigation.navigate("/players"))
            .catch((err) => alert("Error creating player. Check console."));
    };

    render() {

        const { playerName, jerseyNumber, role, errors } = this.state;

        return (
            <div className="container mt-4">
                <h2>Add Player</h2>
                <form onSubmit={this.handleSubmit} noValidate>
                    <div className="form-group mb-3">
                        <label>Player Name:</label>
                        <input
                            type="text"
                            name="playerName"
                            className={`form-control ${errors.playerName ? "is-invalid" : ""}`}
                            value={playerName}
                            onChange={this.handleChange}
                        />
                        {errors.playerName && <div className="invalid-feedback">{errors.playerName}</div>}
                    </div>
                    <div className="form-group mb-3">
                        <label>Jersey Number:</label>
                        <input
                            type="number"
                            name="jerseyNumber"
                            className={`form-control ${errors.jerseyNumber ? "is-invalid" : ""}`}
                            value={jerseyNumber}
                            onChange={this.handleChange}
                        />
                        {errors.jerseyNumber && <div className="invalid-feedback">{errors.jerseyNumber}</div>}
                    </div>
                    <div className="form-group mb-3">
                        <label>Role:</label>
                        <input
                            type="text"
                            name="role"
                            className={`form-control ${errors.role ? "is-invalid" : ""}`}
                            value={role}
                            onChange={this.handleChange}
                            placeholder="Batsman, Bowler, Keeper, All Rounder"
                        />
                        {errors.role && <div className="invalid-feedback">{errors.role}</div>}
                    </div>
                    <button type="submit" className="btn btn-success">Save Player</button>
                </form>
            </div>

        );
    }
}

export default withNavigation(CreatePlayer);