import React from 'react';
import StationMap from './components/StationMap';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

class HelloWorldApp extends React.Component {
  render() {
    return (
      <StationMap/>
    )
  }
}
AppRegistry.registerComponent('BigBridge', () => HelloWorldApp);