import React, { Component } from 'react';
import LinearGradient from 'react-native-linear-gradient';
import { StyleSheet, Text, View, ScrollView, Dimensions, Image } from 'react-native';
class AQIDetails extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        let level;
        let color;
        let value = this.props.value
        let background;
        if (value < 50) {
            // green
            color = "#1b5e20"
            level = "Good"
            background = ['#1b5e20', '#2e7d32', '#388e3c']
        } else if (value < 100) {
            // yellow
            color = "#ffb300"
            level = "Moderate"
            background = ['#ffb300', '#fbc02d', '#fdd835']
        } else if (value < 150) {
            // orange
            color = "ff8f00"
            level = "Unhealthy for Sensitive Groups"
            background = ['#ff8f00', '#ffa000', '#ffb300']
        } else if (value < 200) {
            // red
            color = "c62828"
            level = "Unhealthy"
            background = ['#c62828', '#d32f2f', '#e53935']
        } else if (value < 300) {
            // purple
            color = "6a1b9a"
            level = "Very Unhealthy"
            background = ['#6a1b9a', '#7b1fa2', '#8e24aa']
        } else {
            // dark red
            color = "#3e2723"
            level = "Hazardous"
            background = ['#3e2723', '#4e342e', '#5d4037']
        }
        let pollution = this.props.aqi.map((polluant, index) => {
            return (
                <LinearGradient colors={background}
                    style={[styles.polluantBox,
                    {
                        shadowOffset: {
                            width: 3,
                            height: 3
                        },
                        // shadowColor:'black',
                        elevation: 3,
                        backgroundColor: background[0]
                    }]} key={index}>
                    <View style={{ flexDirection: "row" }}>
                        <Text style={{ fontSize: 15, fontWeight: '100', color: '#FFF', fontFamily: 'Roboto-Light' }}>{polluant.name}</Text>
                    </View>
                    <View style={{ flexDirection: "row", alignItems: 'stretch', marginTop: 10 }}>
                        <Text style={{ fontSize: 30, height: 30, color: '#FFF', fontWeight: '200', textAlignVertical: 'bottom', fontFamily: 'Roboto-Light' }}>{polluant.value[0]}</Text>
                        <Text style={{ fontSize: 15, height: 15, fontWeight: '100', fontFamily: 'Roboto-Light', color: '#FFF', marginLeft: 5, textAlignVertical: 'bottom' }}>{polluant.unity}</Text>
                    </View>
                    <Text style={{ fontSize: 15, height: 15, fontWeight: '100', fontFamily: 'Roboto-Light', color: '#FFF', textAlignVertical: 'bottom' }}>Today : {polluant.value[1]} ~ {polluant.value[2]}</Text>
                    <View style={{ width: '100%', flex: 1, justifyContent: "center" }}>
                        <Image
                            source={{ uri: polluant.srcImage }}
                            style={{ height: 50, width: '100%' }}
                            resizeMode={"contain"} />
                    </View>
                </LinearGradient>
            )
        })
        return (
            <View>
                <View style={{ flexDirection: "column", justifyContent: 'center', marginLeft: 15, marginRight: 15 }}>
                    <Text style={[styles.aqiStyle, { color: background[0] }]}>{this.props.value}</Text>
                    <Text style={{ color: background[0], fontSize: 20, fontFamily: 'Roboto-Light', fontWeight: '100' }}>{level}</Text>
                    <View style={{ flexDirection: "row", height: 15, width: '100%', alignItems: "flex-end" }}>
                        <Image source={{ uri: 'update' }} style={{ height: 15, width: 15 }} resizeMode={"contain"} />
                        <Text style={{ fontFamily: 'Roboto-Light', fontSize: 10 }}>last update: {this.props.time.utc.s}</Text>
                    </View>
                </View>
                <ScrollView style={styles.detailContainer} horizontal={true}>
                    <View style={styles.poluantList}>
                        {pollution}
                    </View>
                </ScrollView>
            </View>
        )
    }
}
const styles = StyleSheet.create({
    detailContainer: {
        width: '100%',
        // height: '100%',
        // padding: 20
    },
    aqiStyle: {
        fontSize: 30,
        fontWeight: '100',
        fontFamily: 'Roboto-Light'
    },
    header: {
        flexDirection: 'row',
        // padding:15,
        marginLeft: 15,
        marginRight: 15,
        alignItems: 'stretch'
    },
    poluantList: {
        flexDirection: 'row',
        flexWrap: 'nowrap',
        justifyContent: 'space-between',
        // backgroundColor:'gray'
    },
    polluantBox: {
        fontFamily: 'Roboto-Light',
        justifyContent: 'flex-start',
        // alignItems:'stretch',
        alignItems: 'baseline',
        textAlignVertical: 'bottom',
        flexDirection: 'column',
        width: Dimensions.get('window').width / 2,
        height: Dimensions.get('window').width / 2,
        backgroundColor: 'orange',
        margin: 15,
        borderRadius: 5,
        padding: 10,
    }
})
export default AQIDetails;