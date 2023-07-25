const util = require('../../utils/util')
const app=getApp()
Page({
  data: {
    comment:"", 
    uid:"",
    pid:"",
  },
  onLoad(options) {
    this.data.uid=wx.getStorageSync('uid')
    this.data.pid=wx.getStorageSync('商品信息').id
  },
  onInput(e){
    this.data[e.currentTarget.dataset.name] = e.detail.value
  },
  submit(){
    
    util.http('/comment/addcomment/vx',this.data,resp=>{
      
    })
    util.redirect('detail')
  }
})