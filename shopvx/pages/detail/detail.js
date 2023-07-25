const util =require("../../utils/util")
const app=getApp()
Page({

  data: {
    api:app.globalData.api,
    item:{},
    comments:[],
    judcollect:0
  },
  onLoad(options) {
    this.data.item=wx.getStorageSync('商品信息')
    var pid =this.data.item.id
    console.log(pid)
    util.http('/product/detail/vx?pid='+pid,resp=>{
      this.data.item=resp.product
      this.data.comments=resp.comments
      this.setData(this.data)
    })
    util.http('/collect/judcollect/vx?uid='+wx.getStorageSync('uid')+'&pid='+pid,resp=>{
      this.data.judcollect=resp.judcollect
      console.log(this.data.judcollect)
    })
  },
  cate_comment(){
    util.redirect('comment')
  },
  cate(){
    util.alert("添加成功")
    util.http('/cart/vxadd?id='+this.data.item.id+'&uid='+wx.getStorageSync('uid'),resp=>{
    })
  },
  cate_order(){
    wx.setStorageSync('pid', this.data.item.pid)
    util.redirect('order')
  },
  detail(e){
    if(this.data.judcollect==0){
      util.http('/collect/addcollect/vx?uid='+wx.getStorageSync('uid')+'&pid='+this.data.item.id,resp=>{
      })
    }else{
      util.http('/collect/deletecollect/vx?uid='+wx.getStorageSync('uid')+'&pid='+this.data.item.id,resp=>{
      })
    }
    this.onLoad()
  }
})