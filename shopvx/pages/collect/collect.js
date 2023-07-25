const util = require('../../utils/util')
const app=getApp()
Page({
  data:{
    api:app.globalData.api,
    collectProducts:[],
    shoppings:[]
  },
  onPullDownRefresh(){
    // this.data.cid=e.currentTarget.dataset.cid
    util.http('/product/index/vx?cid='+this.data.cid,resp=>{
      this.data.products=resp.products
      util.stopPullSetData(this)//停止下拉
    })
  },
  detail(e){
    var item=e.currentTarget.dataset.item
    //存入小程序内部
    wx.setStorageSync('商品信息', item)
    //打开新页面
    util.redirect('detail')
  },
  
  onLoad(){
    var uid = wx.getStorageSync('uid')
    console.log(uid)
    util.http('/collect//getcollect/vx?uid='+uid,resp=>{
      this.data.collectProducts=resp.collectProducts
      this.setData(this.data)
    })
  },
  
})