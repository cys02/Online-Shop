const util = require('../../utils/util')
const app=getApp()
Page({
  data:{
    api:app.globalData.api,
    hots:[],
    categorys:[],
    products:[],
    cid:0,
    zb:[],
    xb:[],
    xl:[],
    wb:[],
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
  cateClick(e){
    this.data.cid=e.currentTarget.dataset.cid
    util.http('/product/index/vx?cid='+this.data.cid,resp=>{
      this.data.products=resp.products
      this.setData(this.data)
    })
  },
  onLoad(){
    util.http('/product/show/vx',resp=>{
      this.data.xb=resp.xb
      this.data.xl=resp.xl
      this.data.wb=resp.wb
      this.data.zb=resp.zb

      this.setData(this.data)
    })
  },
  cate(){
    wx.switchTab({
      url: '../category/category',
    })
  }
})