import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Login from "./containers/Login.js";
import Search from "./containers/Search.js";



import './App.css';

const App = () => {
  return (
      <Router>
          <div className="container">
            <Route path="/" exact component={Login} />
            <Route path="/search" exact component={Search}/>
          </div>
      </Router>
  );
};


export default App;
