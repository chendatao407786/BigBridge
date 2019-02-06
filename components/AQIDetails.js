import React, { Component } from 'react';
import { StyleSheet, Text, View, ToastAndroid, Button, ScrollView, Dimensions } from 'react-native';

class AQIDetails extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        let level;
        let color;
        let value = this.props.value
        if (value < 50) {
            // green
            color = "green"
            level = "Good"
        } else if (value < 100) {
            // yellow
            color = "#EFC916"
            level = "Moderate"
        } else if (value < 150) {
            // orange
            color = "orange"
            level = "Unhealthy {'\n'} for Sensitive Groups"
        } else if (value < 200) {
            // red
            color = "red"
            level = "Unhealthy"
        } else if (value < 300) {
            // purple
            color = "purple"
            level = "Very Unhealthy"
        } else {
            // dark red
            color = "#6E060C"
            level = "Hazardous"
        }
        let pollution = this.props.aqi.map((polluant, index) => {
            return (
                <View style={styles.polluantBox} key={index}>
                    <Text>{polluant.name}</Text>
                    <Text>{polluant.value[0]}</Text>
                </View>
            )
        })
        return (
            <ScrollView style={styles.detailContainer}>
                <View style={styles.header}>
                    <View style={{ flexDirection: "column"}}>
                        <Text style={{fontSize: 15}}>{this.props.name}</Text>
                        <Text style={{fontSize:10}}>{this.props.time.utc.s}</Text>
                    </View>
                    <View style={{ flexDirection: "row", justifyContent: 'center',flex:1 }}>
                        <Text style={{ fontSize: 30, fontWeight: '500', color: color }}>{this.props.value}</Text>
                        <Text style={{ color: color, fontSize: 20 }}>{level}</Text>
                    </View>
                </View>
                <View style={styles.poluantList}>
                    {pollution}
                </View> 
            </ScrollView>

        )
    }
}
const styles = StyleSheet.create({
    header: {
        flexDirection: 'row',
    },
    detailContainer: {
        width: '100%',
        height:'100%'
    },
    poluantList:{
        flexDirection:'row',
        flexWrap: 'wrap',
        justifyContent:'center'
    },
    polluantBox: {
        justifyContent:'center',
        flexDirection: 'column',
        width:Dimensions.get('window').width/2.5,
        height:Dimensions.get('window').width/2.5,
        margin:15,
        backgroundColor:'orange'
    }
})
export default AQIDetails;