// pages/login/login.js
const util = require('../../utils/util')
const app=getApp()
Page({
  data: {
    username:"",
    password:"",
  },
  onLoad(options) {
    if(wx.removeStorageSync('uid')){
      wx.switchTab({
        url: '../index/index',
      })
    }
  },
  onInput(e){
    this.data[e.currentTarget.dataset.name] = e.detail.value
  },
  submit(){
    console.log(this.data.username)
    console.log(this.data.password)
    util.http('/login/vx',this.data,resp=>{
      if(resp.code ==1){
        wx.setStorageSync('uid',resp.uid)
        wx.switchTab({
          url: '../index/index',
        })
      }else{
        util.alert(resp.msg)
      }
    })
  },
  onReady() {

  },
  onShow() {

  },
  onHide() {

  },
  onUnload() {

  },
  onPullDownRefresh() {

  },
  onReachBottom() {

  },
  onShareAppMessage() {

  }
})