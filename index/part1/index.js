/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';
import {
  StyleSheet, View, Image, Text, Dimensions
} from 'react-native';


const {width, heigth} = Dimensions.get('window');

export default class App extends Component {

//  componentDidMount(){
//    codePush.sync({
//      updateDialog: {
//        appendReleaseDescription: true,
//        descriptionPrefix:'\n\n更新内容：\n',
//        title:'更新',
//        mandatoryUpdateMessage:'',
//        mandatoryContinueButtonLabel:'更新',
//      },
//      mandatoryInstallMode:codePush.InstallMode.IMMEDIATE,
//      deploymentKey: "4W944LFwGCJwptFUoBG2FqQqpFAz71f76e94-2011-4f81-a856-8394b47369c3",
//    });
//  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={{width: width}}>一个新的js页面</Text>
        <Image
          resizeMode='stretch'
          style={{height: 100, width: 100}}
          source={require('./image/pic.png')}/>
         <Text> 一个新的js页面 呵呵</Text>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
});
