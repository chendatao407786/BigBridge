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
            color = "green"
            level = "Good"
            background = ['#114B5F', '#1A936F', '#88D498']
        } else if (value < 100) {
            // yellow
            color = "#EFC916"
            level = "Moderate"
            background = ['#938A04', '#9C9304', '#B6AC04']
        } else if (value < 150) {
            // orange
            color = "orange"
            level = "Unhealthy for Sensitive Groups"
            background = ['#AE8005', '#CC970A', '#DCA718']
        } else if (value < 200) {
            // red
            color = "red"
            level = "Unhealthy"
            background = ['#A92E09', '#C0380F', '#DA4518']
        } else if (value < 300) {
            // purple
            color = "purple"
            level = "Very Unhealthy"
            background = ['#56048A', '#8217C5', '#9230D0']
        } else {
            // dark red
            color = "#6E060C"
            level = "Hazardous"
            background = ['#7F0513', '#8C0F1E', '#AC1E2F']
        }
        let pollution = this.props.aqi.map((polluant, index) => {

            return (
                <LinearGradient colors={background} style={[styles.polluantBox, { shadowColor: background[1] }]} key={index}>
                    <View style={{flexDirection:"row"}}>
                        <Text style={{ fontSize: 15, fontWeight: '100', color: '#FFF', fontFamily: 'Roboto-Light' }}>{polluant.name}</Text>
                    </View>
                    <View style={{ flexDirection: "row", alignItems: 'stretch', marginTop: 10 }}>
                        <Text style={{ fontSize: 30, height: 30, color: '#FFF', fontWeight: '200', textAlignVertical: 'bottom', fontFamily: 'Roboto-Light' }}>{polluant.value[0]}</Text>
                        <Text style={{ fontSize: 15, height: 15, fontWeight: '100', fontFamily: 'Roboto-Light', color: '#FFF', marginLeft: 5, textAlignVertical: 'bottom' }}>{polluant.unity}</Text>
                    </View>
                    <Text style={{ fontSize: 15, height: 15, fontWeight: '100', fontFamily: 'Roboto-Light', color: '#FFF', textAlignVertical: 'bottom' }}>Today : {polluant.value[1]} ~ {polluant.value[2]}</Text>
                    {/* <Image source={{uri:'assets:/images/so2.png'}} style={{width:20,height:20}}/> */}
                    <View style={{width:'100%',flex:1,justifyContent:"center"}}>
                        <Image 
                                source={{ uri: polluant.srcImage }} 
                                style={{ height: 50,width:'100%' }} 
                                resizeMode={"contain"} />
                    </View> 
                </LinearGradient>
            )
        })
        return (
            // <ScrollView style={styles.detailContainer} horizontal={true}>
            <View>

                {/* <View style={styles.header}> */}
                    {/* <Image source={{uri: 'https://cityos.io/theme/page/air/images/donut-pm2.png'}} style={{width:20,height:20}}/> */}
                    {/* <View style={{ flexDirection: "column" }}>
                        <Text style={{ fontSize: 10 }}>last update: {this.props.time.utc.s}</Text>
                    </View> */}
                    <View style={{ flexDirection: "column", justifyContent: 'center',marginLeft: 15,marginRight: 15}}> 
                        <Text style={[styles.aqiStyle, { color: background[0] }]}>{this.props.value}</Text>
                        <Text style={{ color: background[0], fontSize: 20, fontFamily: 'Roboto-Light', fontWeight: '100' }}>{level}</Text>
                        <View style={{flexDirection:"row",height:15,width:'100%',alignItems:"flex-end"}}>
                            <Image source={{uri:'update'}} style={{height:15,width:15}} resizeMode={"contain"}/>
                            <Text style={{fontFamily:'Roboto-Light',fontSize:10}}>last update: {this.props.time.utc.s}</Text>
                         </View>
                    </View>
                {/* </View> */}
                <ScrollView style={styles.detailContainer} horizontal={true}>
                    <View style={styles.poluantList}>
                        {pollution}
                    </View>
                </ScrollView>
                {/* <Text style={{ fontSize: 10, fontFamily: 'Roboto-Light', paddingLeft: 15 }}>last update: {this.props.time.utc.s}</Text> */}
            </View>
            // </ScrollView>
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