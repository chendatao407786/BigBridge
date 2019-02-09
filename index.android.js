import React from 'react';
import StationMap from './components/StationMap';
import {
  AppRegistry
} from 'react-native';

class HelloWorldApp extends React.Component {
  render() {
    return (
      <StationMap/>
    )
  }
}
AppRegistry.registerComponent('BigBridge', () => HelloWorldApp);