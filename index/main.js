/**
 * Created by puxiang on 2018/2/8.
 */
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';
import {
  StyleSheet, View, Image, Text, Dimensions, NativeModules
} from 'react-native';

import Part1 from './part1/index';
import Part2 from './part2/index';


const {width, heigth} = Dimensions.get('window');

export default class App extends Component {
  state = {
    type: 0,
  }

  componentDidMount() {
    NativeModules.InitReact.getInitState().then(res => {
      console.log('InitReact', res);
      if (res === 1) {
        this.setState({
          type: 1
        })
      } else if (res === 2) {
        this.setState({
          type: 2
        })
      }
    }).catch(err => {

    })
  }

  render() {
    console.log('App', this.props.type);
    if (this.state.type === 1) {
      return (
        <Part1/>
      )
    } else if (this.state.type === 2) {
      return (
        <Part2/>
      )
    } else {
      return null;
    }
  }
}
