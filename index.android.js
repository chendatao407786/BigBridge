import React from 'react';
import AppNavigator from './components/nav/AppNavigator'
// import Navigator from 'react-native'

import { Text, Navigator, TouchableHighlight } from 'react-native';
import StationMap from './components/StationMap';
import {
  AppRegistry
} from 'react-native';

class HelloWorldApp extends React.Component {
  render() {
    return (
      <StationMap/>
      // <Navigator
      //   initialRoute={{ title: 'My Initial Scene', index: 0 }}
      //   renderScene={(route, navigator) => {
      //     return <MyScene title={route.title} />
      //   }}
      // />
      // <AppNavigator id='Settings' data='' name='' component={Settings} />
    )
  }
}
AppRegistry.registerComponent('BigBridge', () => HelloWorldApp);