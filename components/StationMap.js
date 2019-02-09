import React, { Component } from 'react';
import { StyleSheet, Text, View, Dimensions, StatusBar } from 'react-native';
import MapView, { Marker, Callout } from 'react-native-maps';
import AQIDetails from './AQIDetails';
import CustomCallout from './CustomCallout';


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
        this.markerColor = this.markerColor.bind(this);
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

    createPolluant(name, value, update_time) {
        return ({
            name: name,
            value: value,
            update: update_time
        })
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
                    let newPolluant;
                    switch (polluant.p) {
                        case 'pm25':
                            newPolluant = { name: "PM2.5", value: polluant.v, update: polluant.h, unity: 'AQI',srcImage:"pm25" };
                            break;
                        case 'pm10':
                            newPolluant = { name: "PM10", value: polluant.v, update: polluant.h, unity: 'AQI',srcImage:"pm10"  };
                            break;
                        case 'o3':
                            newPolluant = { name: "O3", value: polluant.v, update: polluant.h, unity: 'AQI',srcImage:"o3"  };
                            break;
                        case 'no2':
                            newPolluant = { name: "NO2", value: polluant.v, update: polluant.h, unity: 'AQI',srcImage:"no2"  }
                            break;
                        case 'so2':
                            newPolluant = { name: "SO2", value: polluant.v, update: polluant.h, unity: 'AQI',srcImage:"so2"  }
                            break;
                        case 't':
                            newPolluant = { name: "Temperature", value: polluant.v, update: polluant.h, unity: '°C' ,srcImage:"temperature" }
                            break;
                        case 'p':
                            newPolluant = { name: "Atmos.", value: polluant.v, update: polluant.h, unity: 'MBAR',srcImage:"atmos"  }
                            break;
                        case 'h':
                            newPolluant = { name: "Humidity", value: polluant.v, update: polluant.h, unity: '%',srcImage:"humidity"  }
                            break;
                        case 'w':
                            newPolluant = { name: "Wind", value: polluant.v, update: polluant.h, unity: 'M/S' ,srcImage:"wind" }
                            break;
                        case 'co':
                            newPolluant = { name: "CO", value: polluant.v, update: polluant.h, unity: 'AQI' ,srcImage:"co" }
                            break;
                        default:
                            newPolluant = { name: String(polluant.p).toUpperCase(), value: polluant.v, update: polluant.h, unity: '' }
                    }
                    polluants.push(newPolluant);
                });
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

    markerColor = (value) => {
        let bkcolor;
        if (value <= 50) {
            bkcolor = { backgroundColor: '#1A936F' };
        } else if (value <= 100) {
            bkcolor = { backgroundColor: '#9C9304' };
        } else if (value <= 150) {
            bkcolor = { backgroundColor: '#CC970A' };
        } else if (value <= 200) {
            bkcolor = { backgroundColor: '#C0380F' };
        } else if (value <= 300) {
            bkcolor = { backgroundColor: '#8217C5' };
        } else if (value > 300) {
            bkcolor = { backgroundColor: '#8C0F1E' };
        } else {
            bkcolor = { backgroundColor: 'gray' };
        }
        return bkcolor;
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
                                <Marker
                                    id={station.id}
                                    coordinate={station.coordinate}
                                    // calloutOffset={{ x: -1, y: 1 }}
                                    // calloutAnchor={{ x: 0.5, y: 0.1 }}
                                    onPress={e => this.onPressHandler(station.id)}
                                >
                                    <View>
                                        <View style={[this.markerColor(station.value), styles.marker]}>
                                            <Text style={styles.text}>{station.value}</Text>
                                        </View>
                                        <Callout tooltip style={styles.customView}>
                                            <CustomCallout>
                                                <Text style={{ fontFamily: 'Roboto-Light', fontSize: 15, textAlign: 'center',fontWeight:"300"}}>{station.name}</Text>
                                            </CustomCallout>
                                        </Callout>
                                    </View>
                                </Marker>
                                // <Marker
                                //     id={station.id}
                                //     // title={station.name}
                                //     coordinate={station.coordinate}
                                //     key={station.id}
                                //     ref={marker => {
                                //         this.marker1 = marker;
                                //       }}
                                //     onPress={e => this.onPressHandler(station.id)}
                                //     onCalloutPress={() => {
                                //         console.log("Marker/onCalloutPress");
                                //         this.marker1.hideCallout();
                                //     }}>
                                //     {/* <Callout tooltip={true} style={{width:20,height:20}}>
                                //         <View>
                                //             <Text>
                                //                 callout text
                                //             </Text>
                                //         </View>
                                //     </Callout> */}
                                //     <View style={[this.markerColor(station.value), styles.marker]}>
                                //         <Text style={styles.text}>{station.value}</Text>
                                //     </View>
                                // </Marker>
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
        flex: 1
    },
    customView: {
        top:-5
        // width: 140,
        // height: 100
    },
    map: {
        width: '100%',
        height: 300,
        shadowOffset: { width: 5, height: 5 },
        shadowOpacity: 0.5,
        shadowRadius: 5
    },
    detailContainer: {
        // backgroundColor:'steelblue',
        // height:100
        // backgroundColor:'#DADADA',
        height: (Dimensions.get('window').height - 300 - 56 - StatusBar.currentHeight)
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
        textAlign: 'center',
        fontFamily: 'Roboto-Light'
    }
})

export default StationMap;