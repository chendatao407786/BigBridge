import React from 'react';
import PropTypes from 'prop-types';

import {
    StyleSheet,
    View
} from 'react-native';

const propTypes = {
    children: PropTypes.node.isRequired,
    style: PropTypes.object,
};

class CustomCallout extends React.Component {
    render() {
        return (
            <View style={[styles.container, this.props.style]}>
                <View style={styles.bubble}>
                    <View style={styles.amount}>
                        {this.props.children}
                    </View>
                </View>
                <View style={styles.arrowBorder} />
                <View style={styles.arrow} />
            </View>
        );
    }
}

CustomCallout.propTypes = propTypes;

const styles = StyleSheet.create({
    container: {
        flexDirection: 'column',
        alignSelf: 'flex-start',
    },
    bubble: {
        // width:200,
        minWidth:150,
        flex:1,
        alignSelf:'center',
        flexDirection: 'row',
        backgroundColor: '#FFF',
        paddingHorizontal: 5,
        // paddingVertical: 12,
        borderRadius: 6,
        borderColor: '#FFF',
        borderWidth: 0.5,
    },
    amount: {
        flex:1,
    },
    arrow: {
        backgroundColor: 'transparent',
        borderWidth: 10,
        borderColor: 'transparent',
        borderTopColor: '#FFF',
        alignSelf: 'center',
        marginTop: -20,
    },
    arrowBorder: {
        backgroundColor: 'transparent',
        borderWidth: 10,
        borderColor: 'transparent',
        borderTopColor: '#FFF',
        alignSelf: 'center',
        marginTop: -0.5,
    },
});

export default CustomCallout;