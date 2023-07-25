const util = require('../../utils/util')
const app=getApp()
Page({
  data:{
    api:app.globalData.api,
    hots:[],
    categorys:[],
    products:[],
    cid:0,
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
    console.log(item)
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
    util.http('/product/index/vx',resp=>{
      console.log(resp)
      this.data.hots=resp.hots
      this.data.categorys = resp.categorys
      if(resp.categorys.length >0){
        this.data.cid = resp.categorys[0].id
      }
      this.data.products =resp.products
      //将变化的数据更新到页面中
      this.setData(this.data)
    })
  }
})