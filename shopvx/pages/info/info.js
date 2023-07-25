const util = require('../../utils/util')
const app=getApp()
Page({
  data: {
    info:"", 
    description:"",
    uid:""
  },
  onLoad(options) {
    this.data.uid=wx.getStorageSync('uid')
  },
  onInput(e){
    this.data[e.currentTarget.dataset.name] = e.detail.value
  },
  submit(){
    console.log(this.data)
    util.http('/info/addinfo/vx',this.data,resp=>{
      
    })
    wx.switchTab({
      url: '../category/category',
    })
  }
})