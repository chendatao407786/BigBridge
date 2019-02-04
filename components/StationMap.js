import React, { Component } from 'react';
import { StyleSheet, Text, View, ToastAndroid, Button, PermissionsAndroid } from 'react-native';
import axios from 'axios';
import MapView, { Marker } from 'react-native-maps';
let id = 0;
let url
class StationMap extends Component {
    constructor(props) {
        super(props);
        this.state = {
            stations: [],
            initPosition: null,
        }
        this.getUserLocation = this.getUserLocation.bind(this);
        this.getNearestStation = this.getNearestStation.bind(this);
        this.onPressHandler = this.onPressHandler.bind(this);
    }
    componentWillMount() {
        id++;
        this.getUserLocation();

        // if(this.state.initPosition!=null){
        //     this.getNearestStation();
        // }
    };

    getNearestStation = (latitude, longitude) => {
        url = 'https://api.waqi.info/mapq/nearest/?n=10&geo=1/' + latitude + '/' + longitude;
        fetch(url, {
            method: 'GET',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then(res => res.json())
            .then(resJson => {
                let newStations = [];
                resJson.d.map(station => {
                    let oneStation = {
                        name: station.nlo,
                        id: station.x,
                        value: station.v,
                        coordinate: {
                            latitude: Number(station.geo[0]),
                            longitude: Number(station.geo[1])
                        }
                    }
                    newStations.push(oneStation);
                    // newCity.push(station.nlo)
                    // newMarkers.push(oneMarker);
                    // this.setState({
                    // markers: [station.geo, ...this.state.markers],
                    // })
                })
                this.setState({
                    stations: newStations
                })
            }
            )
            .catch(e => {
                console.error(e);
            });
        // axios
        //     .get('https://api.waqi.info/mapq/nearest/?n=1')
        //     .then(res => {
        //         console.log(res);
        //     },e => {
        //         console.log(e);
        //     })
    }
    getUserLocation = () => {
        navigator.geolocation.getCurrentPosition(position => {
            console.log(position);
            this.getNearestStation(position.coords.latitude, position.coords.longitude);
            this.setState({
                initPosition: position
            })
        }, err => console.log(err))
    }
    onPressHandler(e){
        console.log(e.nativeEvent);
    }
    render() {

        if (this.state.initPosition === null) {
            return (
                <Text>Loading....{id}</Text>
            )
        } else {
            // this.getNearestStation(this.state.initPosition.coords.latitude, this.state.initPosition.coords.longitude);
            return (
                <View style={styles.mapContainer}>
                    <MapView
                        style={styles.map}
                        showsUserLocation
                        initialRegion={{
                            latitude: this.state.initPosition.coords.latitude,
                            longitude: this.state.initPosition.coords.longitude,
                            latitudeDelta: 0.0922,
                            longitudeDelta: 0.0421,
                        }}>
                        {
                            this.state.stations.map((station) => (
                                <MapView.Marker
                                    id={station.id}
                                    title={station.name}
                                    coordinate={station.coordinate}
                                    key={station.id}
                                    // onPress={this.onPressHandler(station.id)} 
                                    >
                                    <View style={station.value<50?styles.marker_green:styles.marker_orange} onMarkerPress={e => this.onPressHandler(e)}>
                                        <Text style={styles.text}>{station.value}</Text>
                                    </View>
                                </MapView.Marker>
                            ))
                        }
                    </MapView>
                    
                    {/* <Button title="test" onPress={this.onPressHandler()} /> */}
                </View>
            )
        }
    }
}

const styles = StyleSheet.create({
    mapContainer: {
        width: '100%',
        height: 400
    },
    map: {
        width: '100%',
        height: '100%'
    },
    marker_green:{
        width:20,
        height:20,
        backgroundColor:'green',
        borderRadius:10
    },
    marker_orange:{
        backgroundColor:'orange',
        width:20,
        height:20,
        borderRadius:10
    },
    text:{
        color:'#FFF',
        fontSize:10,
        lineHeight:20,
        textAlign:'center'
    }
})

export default StationMap;