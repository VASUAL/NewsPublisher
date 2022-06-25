<template>
  <!--头部-->
  <HeadOfHome/>
  <div class="dF">
    <!--新闻内容-->
    <table class="t1">
      <tr class="tr1">
        <td colspan="2" class="td1">[<span id="type"></span>]&nbsp;&nbsp;<span id="headline"></span></td>
      </tr>
      <tr>
        <td colspan="2" id="content"> </td>
      </tr>
    </table>
    <!--分页组件-->
    <div>
      <el-button @click="goPrint" style="margin-left: 100vh">下载</el-button>
    </div>
    <el-pagination
        background
        layout="total, prev, pager, next"
        :total="total"
        :page-size="pageSize"
        v-model:currentPage="currentPage"
        @current-change="handleCurrentChange"
        class="el8">
    </el-pagination>
  </div>
  <el-backtop />
</template>

<script>
import request from "../utils/request";
import $ from "../assets/js/jquery-3.6.0";
import moment from "moment";
import HeadOfHome from "../components/HeadOfHome";
import printJS from 'print-js'
import html2Canvas from 'html2canvas'
let newsId;
let newsAuthor;
let userEmail;
export default {
  name: "NewsContent",
  components: {
    HeadOfHome
  },
  data() {
    return {
      boyHeadAuthor: false,
      girlHeadAuthor: false,
      total: 0,
      pageSize: 5,
      currentPage: 1,
    }
  },
  methods: {
    handleCurrentChange() {
      this.loadCommentData()
    },
    //加载新闻内容数据
    loadNewsData() {
      newsId = this.$route.query.newsId
      console.log(newsId)
      //使该新闻的点击量加1
      request.post("/news/increaseNewsClick",newsId).then(res =>{
        request.post("/news_User/getNewsById",newsId).then(res =>{
          $('#type').text(res.data.type)
          $('#headline').text(res.data.headline)
         if (res.data.sex==="男") {
            this.boyHeadAuthor = true
            this.girlHeadAuthor = false
          }else {
            this.boyHeadAuthor = false
            this.girlHeadAuthor = true
          }
          $('#author').text(res.data.petname)
          $('#date').text(moment(res.data.date).format("YYYY-MM-DD"))
          $('#content').html(res.data.content)
          newsAuthor = res.data.author
        })
      })
    },
    goPrint() {
      this.isPrint = true
      html2Canvas(this.$refs.print, {
        allowTaint: true,
        taintTest: false,
        useCORS: true,
        dpi: window.devicePixelRatio * 4,
        scale: 4
      }).then((canvas) => {
        const url = canvas.toDataURL()
        printJS({
          printable: url, // 要打印的id
          type: 'image',
          style: '@page{size:auto;margin: 0cm 1cm 0cm 1cm;}' //去除页眉页脚
        })
        this.isPrint = false
      })
    }
  },
  created() {
    this.loadNewsData()
    /*
    let str = sessionStorage.getItem("userEmail")
    if (str===null||str==="") {
      userEmail = str
    }else {
      let reg = new RegExp('"', "g");
      userEmail = str.replace(reg, "");    //正则表达式，将字符串两端的双引号去掉
    }*/
  }
}
</script>

<style scoped>
.el-table tbody tr:hover>td {
  background-color:#ffffff!important
}
.dF{
  position: absolute;height: 100vh;width: 60%;background: white;left: 18.8%;margin-top: 70px;padding-right: 1.2%;padding-left: 1.2%;box-shadow: 0 0 3px 3px #f3f3f3;
}
.t1{
  width: 100%;
}
.tr1{
  height: 80px;
}
.td1{
  font-size: 22px
}
.tr2{
  height: 60px;position: relative;top: -10px;left: 10px;
}
.td2{
  width: 6%;
}
.boy{
  height: 40px;width: 40px;border-radius: 20px;
}
.girl{
  height: 40px;width: 40px;border-radius: 20px;
}
.el1{
  text-decoration: none;color: red;font-size: 15px;
}
.el2{
  text-decoration: none;color: red;font-size: 15px;
}
.t2{
  width: 100%;height: 250px;
}
.td3{
  text-align: center;padding-top: 30px;
}
.td4{
  text-align: center;padding-top: 30px;
}
.el3{
  width: 100%;
}
.t3{
  width: 100%;
}
.tr3{
  background: #fafafa;
}
.td5{
  width: 15%;text-align: center;
}
.dS{
  padding: 20px;
}
.im1{
  height: 50px;width: 50px;padding: 15px;padding-bottom: 20px;
}
.sp1{
  font-size: 15px;
}
.el4{
  text-decoration: none;color: red;font-size: 13px;position: relative;top: 5px;
}
.el5{
  text-decoration: none;color: red;font-size: 13px;position: relative;top: 8px;
}
.td6{
  padding: 10px;
}
.tr4{
  font-size: 12px;text-align: right;
}
.td7{
  padding-bottom: 20px;
}
.t4{
  width: 100%;
}
.tr5{
  background: #fafafa;
}
.td8{
  width: 15%;text-align: center;
}
.dT{
  padding: 20px;
}
.im2{
  height: 50px;width: 50px;padding: 15px;padding-bottom: 20px;
}
.sp2{
  font-size: 15px;
}
.el6{
  text-decoration: none;color: red;font-size: 13px;position: relative;top: 5px;
}
.el7{
  text-decoration: none;color: red;font-size: 13px;position: relative;top: 8px;
}
.td9{
  padding: 10px;
}
.tr6{
  font-size: 12px;text-align: right;
}
.td10{
  padding-bottom: 20px;
}
.el8{
  margin-top: 70vh;
}
.d5{
  text-align: center
}
.el9{
  padding-top: 30px;padding-bottom: 30px;
}
.el10{
  margin-bottom: 60px;
}
</style>