import React, { Component } from 'react';
import { StyleSheet, Text, View, ToastAndroid, Button, PermissionsAndroid, ScrollView,Dimensions,StatusBar } from 'react-native';
import axios from 'axios';
import MapView, { Marker } from 'react-native-maps';
import AQIDetails from './AQIDetails';


class StationMap extends Component {
    constructor(props) {
        super(props);
        this.state = {
            stations: [],
            initPosition: null,
            currentStation: null
        }
        this.getUserLocation = this.getUserLocation.bind(this);
        this.getNearestStation = this.getNearestStation.bind(this);
        this.onPressHandler = this.onPressHandler.bind(this);
    }
    componentWillMount() {

        this.getUserLocation();

        // if(this.state.initPosition!=null){
        //     this.getNearestStation();
        // }
    };

    getNearestStation = (latitude, longitude) => {
        let url = 'https://api.waqi.info/mapq/nearest/?n=10&geo=1/' + latitude + '/' + longitude;
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
    onPressHandler(id) {
        let url = 'https://api.waqi.info/api/feed/@' + id + '/obs.fr.json'
        console.log(url);
        fetch(url, {
            method: 'GET',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            }
        })
            .then(res => {
                res = JSON.parse(res._bodyText).rxs.obs[0].msg;
                console.log(res);
                let polluants = [];
                res.iaqi.map(polluant => {
                    let newPolluant = {
                        name: polluant.p,
                        value: polluant.v,
                        update: polluant.h,
                    }
                    polluants.push(newPolluant);
                })
                let station = {
                    aqi: res.aqi,
                    time: res.time,
                    city: res.city.name,
                    polluants: polluants,
                };
                console.log(station);
                this.setState({
                    currentStation: station
                })
            })
            .catch(e => {
                console.log(e);
            })
    }


    render() {
        // let station = (
        //     <Text>{this.state.currentStation}</Text>
        // )
        if (this.state.initPosition === null) {
            return (
                <Text>Loading....</Text>
            )
        } else {
            return (
                <View style={styles.mainContainer}>
                    <MapView
                        style={styles.map}
                        showsUserLocation
                        initialRegion={{
                            latitude: this.state.initPosition.coords.latitude,
                            longitude: this.state.initPosition.coords.longitude,
                            latitudeDelta: 0.122,
                            longitudeDelta: 0.0821,
                        }}>
                        {
                            this.state.stations.map((station) => (
                                <MapView.Marker
                                    id={station.id}
                                    title={station.name}
                                    coordinate={station.coordinate}
                                    key={station.id}
                                    onPress={e => this.onPressHandler(station.id)}>
                                    <View style={[station.value < 50 ? styles.marker_green : styles.marker_orange, styles.marker]}>
                                        <Text style={styles.text}>{station.value}</Text>
                                    </View>
                                </MapView.Marker>
                            ))
                        }
                    </MapView>
                    <View style={styles.detailContainer}>
                    {this.state.currentStation != null ?
                        <AQIDetails
                            name={this.state.currentStation.city}
                            value={this.state.currentStation.aqi}
                            aqi={this.state.currentStation.polluants}
                            time={this.state.currentStation.time} />
                        :
                        <Text>Nothing here</Text>}
                    </View>  
                    {/* <View style={styles.tab}></View> */}
                </View>
            )
        }
    }
}

const styles = StyleSheet.create({
    mainContainer: {
        width: '100%',
        flex:1
    },
    map: {
        width: '100%',
        height: 400
    },
    detailContainer:{
        // backgroundColor:'steelblue',
        // height:100
        height:(Dimensions.get('window').height-400-56-StatusBar.currentHeight)
    },
    
    marker: {
        width: 20,
        height: 20,
        borderRadius: 10
    },
    marker_green: {
        backgroundColor: 'green',
    },
    marker_orange: {
        backgroundColor: 'orange',
    },
    text: {
        color: '#FFF',
        fontSize: 10,
        lineHeight: 20,
        textAlign: 'center'
    }
})

export default StationMap;