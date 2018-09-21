import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import "./Login.css";
import axios from "axios";


export default class Search extends Component {
  constructor(props) {
    super(props);

    this.state = {
      text1: "",
      text2: ""
    };
  }

  validateForm() {
    return this.state.text1.length > 0;
  }
  validateForm2() {
    return this.state.text2.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  handleSubmit = event => {
    //searching singers
    //GET request
    event.preventDefault();

    var query = this.state.text1.toUpperCase();
    query = query.replace(" ", "%20");


    axios.get('http://localhost:8080/searchSingerName/' + query)
    .then(response =>
      document.getElementById("searchResult").innerHTML = 'Artist Name:' + response.data.name + '<br />' + 'DOB:' + response.data.dob+ '<br />' + 'SEX:' + response.data.sex)
  }
  handleSubmit2 = event => {
    //searching albums
    //GET request
    event.preventDefault();
    var query = this.state.text2.toUpperCase();
    query = query.replace(" ", "%20");


    axios.get('http://localhost:8080/searchAlbumName/' + query)
    .then(response => document.getElementById("searchResult").innerHTML = 'Album Name:' +response.data.albumName + '<br />Singer Name:' + response.data.singerName + '<br />Release Year:' + response.data.releaseYear + '<br />Record Comapny:' + response.data.recordCompany)




  }

  render() {
    return (
      <div className="Search">
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="text1" bsSize="large">
            <ControlLabel>Search Singer</ControlLabel>
            <FormControl
              autoFocus
              type="name"
              value={this.state.text1}
              onChange={this.handleChange}
            />
          </FormGroup>

          <Button
            block
            bsSize="large"
            disabled={!this.validateForm()}
            type="submit"
          >
            Search
          </Button>
        </form>
        <form onSubmit={this.handleSubmit2}>
          <FormGroup controlId="text2" bsSize="large">
            <ControlLabel>Search Album</ControlLabel>
            <FormControl
              autoFocus
              type="name"
              value={this.state.text2}
              onChange={this.handleChange}
            />
          </FormGroup>

          <Button
            block
            bsSize="large"
            disabled={!this.validateForm2()}
            type="submit"
          >
            Search
          </Button>
        </form>
        <p id="searchResult"/>
      </div>
    );
  }
}
