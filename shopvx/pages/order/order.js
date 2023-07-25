const util = require('../../utils/util')
const app=getApp()
Page({
  data: {
    name:"",
    mobile:"",
    address:"",
    count:"",
    uid:"",
    pid:"",
    id:"",
  },
  onLoad(options) {
    this.data.uid=wx.getStorageSync('uid')
    this.data.pid=wx.getStorageSync('商品信息').id
  },
  onInput(e){
    this.data[e.currentTarget.dataset.name] = e.detail.value
  },
  submit(){
    var date = new Date();
    var month = (date.getMonth() + 1).toString().padStart(2, '0');
    var day = date.getDate().toString().padStart(2, '0');
    var hours = date.getHours().toString().padStart(2, '0');
    var minutes = date.getMinutes().toString().padStart(2, '0');
    var seconds = date.getSeconds().toString().padStart(2, '0');
    this.data.id = month + day + hours + minutes + seconds;
    console.log(this.data)
    util.http('/order/addorder/vx',this.data,resp=>{
      
    })
    wx.switchTab({
      url: '../index/index',
    })
  }
})