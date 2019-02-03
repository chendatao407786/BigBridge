import React, { Component } from 'react';
import { StyleSheet, Text, View, ToastAndroid, Button,PermissionsAndroid } from 'react-native';

import MapView from 'react-native-maps';
class StationMap extends Component {
    constructor(props) {
        super(props);
        this.state = {
            position: {}
        }
        this.getUserLocation = this.getUserLocation.bind(this);
        this.onPressHandler = this.onPressHandler.bind(this);
        this.requestLocationPermission = this.requestLocationPermission.bind(this);
    }
    componentWillMount() {
        // this.getUserLocation();
    }

    async requestLocationPermission(){
        try{
            const granted = await PermissionsAndroid.request(
                PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
                {
                    title: 'We need your location',
                    message: 'We need your location to detect the nearest station',
                    buttonNeutral:'Ignor',
                    buttonNegative:'Cancel',
                    buttonPositive:'OK'
                });
                if(granted === PermissionsAndroid.RESULTS.GRANTED){
                    console.log("OK");
                    getUserLocation();
                }else{
                    console.log("NOT OK");
                }
            }catch(e){
                console.log(e);
        }
    }    
    getUserLocation = () => {
        navigator.geolocation.getCurrentPosition(position => {
            console.log(position);
            this.setState({
                position: position
            })
        }, err => console.log(err))
    }
    onPressHandler = () => {
        // this.getUserLocation();
        this.requestLocationPermission();
        console.log(this.state.position);
    }
    render() {
        return (
            <View style={styles.mapContainer}>
                {/* <Text>See me?</Text> */}
                {/* <Toast visible={this.state.position} message="Location" /> */}
                <MapView
                    style={styles.map}
                    initialRegion={{
                        latitude: 37.78825,
                        longitude: -122.4324,
                        latitudeDelta: 0.0922,
                        longitudeDelta: 0.0421,
                    }}></MapView>
                <Button title="Test" onPress={this.onPressHandler} ></Button>
            </View>
        )
    }
}
const styles = StyleSheet.create({
    mapContainer: {
        width: '100%',
        height: 200
    },
    map: {
        width: '100%',
        height: '100%'
    }
})

export default StationMap;