// pages/bag/bag.js
const util =require("../../utils/util")
const app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bags:[],
    uid:0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.data.uid = wx.getStorageSync('uid')
    util.http('/cart/vxcart?uid='+this.data.uid,resp=>{
      this.data.bags=resp.cartProducts
      this.setData(this.data)

    })
  },
  onPullDownRefresh(){
    // this.data.cid=e.currentTarget.dataset.cid
    util.http('/product/index/vx?cid='+this.data.cid,resp=>{
      this.data.products=resp.products
      util.stopPullSetData(this)//停止下拉
    })
  },
})