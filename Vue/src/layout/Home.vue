<template>
  <!--头部-->
  <HeadOfHome/>
  <!--导航栏-->
  <div class="divF">
    <router-link to="/" class="l1">首页</router-link>
    <router-link to="/latestNews" class="l2">最新</router-link>
    <router-link to="/hotNews" class="l3">热门</router-link>
  </div>
  <!--中间部分-->
  <div class="divS">
    <div class="divT">
      <span @click="$router.push({path: '/typeContent',query: {typeName: '国际新闻'}})" class="sp1">国际快讯</span>
    </div>
    <el-divider>
      <el-icon><star-filled /></el-icon>
    </el-divider>
    <el-table :data="tableDataForGlobalNews" class="el1" :show-header="false" :cell-style="{background:'#ffffff'}">
      <el-table-column align="center">
        <template #default="scope" class="el1">
          <table class="el1">
            <tr class="tr1">
              <td class="td1"><span @click="intoNewsContent(scope.row.id)" class="sp2">{{scope.row.headline}}</span></td>
              <td class="td2">{{scope.row.date}}</td>
              <td class="td2">{{scope.row.click}}&nbsp;&nbsp;浏览</td>
            </tr>
          </table>
        </template>
      </el-table-column>
    </el-table>
    <div class="divT">
      <span @click="$router.push({path: '/typeContent',query: {typeName: '国内新闻'}})" class="sp1">聚焦国内</span>
    </div>
    <el-divider>
      <el-icon><star-filled /></el-icon>
    </el-divider>
    <el-table :data="tableDataForChinaNews" class="el1" :show-header="false" :cell-style="{background:'#ffffff'}">
      <el-table-column align="center">
        <template #default="scope" class="el1">
          <table class="el1">
            <tr class="tr1">
              <td class="td1"><span @click="intoNewsContent(scope.row.id)" class="sp2">{{scope.row.headline}}</span></td>
              <td class="td2">{{scope.row.date}}</td>
              <td class="td2">{{scope.row.click}}&nbsp;&nbsp;浏览</td>
            </tr>
          </table>
        </template>
      </el-table-column>
    </el-table>
    <div class="divT">
      <span @click="$router.push({path: '/typeContent',query: {typeName: '江苏新闻'}})" class="sp1">江苏新闻</span>
    </div>
    <el-divider>
      <el-icon><star-filled /></el-icon>
    </el-divider>
    <el-table :data="tableDataForHenanNews" class="el1" :show-header="false" :cell-style="{background:'#ffffff'}">
      <el-table-column align="center">
        <template #default="scope" class="el1">
          <table class="el1">
            <tr class="tr1">
              <td class="td1"><span @click="intoNewsContent(scope.row.id)" class="sp2">{{scope.row.headline}}</span></td>
              <td class="td2">{{scope.row.date}}</td>
              <td class="td2">{{scope.row.click}}&nbsp;&nbsp;浏览</td>
            </tr>
          </table>
        </template>
      </el-table-column>
    </el-table>
    <div class="divT">
      <span @click="$router.push({path: '/typeContent',query: {typeName: '扬州大事'}})" class="sp1">扬州大事</span>
    </div>
    <el-divider>
      <el-icon><star-filled /></el-icon>
    </el-divider>
    <el-table :data="tableDataForZZNews" class="el1" :show-header="false" :cell-style="{background:'#ffffff'}">
      <el-table-column align="center">
        <template #default="scope" class="el1">
          <table class="el1">
            <tr class="tr1">
              <td class="td1"><span @click="intoNewsContent(scope.row.id)" class="sp2">{{scope.row.headline}}</span></td>
              <td class="td2">{{scope.row.date}}</td>
              <td class="td2">{{scope.row.click}}&nbsp;&nbsp;浏览</td>
            </tr>
          </table>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <!--尾部-->
  <div class="divF2">
    <a href="http://www.yzu.edu.cn/" class="a1">Designed in Yzu</a>
  </div>

</template>

<script>

import HeadOfHome from "../components/HeadOfHome";
import request from "../utils/request";
import moment from "moment";
import StarFilled from "@element-plus/icons/lib/StarFilled";

export default {
  name: "Home",
  components: {
    HeadOfHome,
    StarFilled,
  },
  data() {
    return {
      tableDataForGlobalNews: [

      ],
      tableDataForChinaNews: [

      ],
      tableDataForHenanNews: [

      ],
      tableDataForZZNews: [

      ]
    }
  },
  methods: {
    //进入新闻详情页
    intoNewsContent(id) {
      this.$router.push({
        path: '/newsContent',
        query: {
          newsId: id
        }
      })
    },
    //加载国际新闻
    loadDataForGlobalNews() {
      request.post("/news/getNewsByNewsType","国际新闻").then(res =>{
        for (let i=0;i<res.data.length;i++) {
          res.data[i].date = moment(res.data[i].date).format("YYYY-MM-DD")
        }
        this.tableDataForGlobalNews = res.data
      })
    },
    //加载国内新闻
    loadDataForChinaNews() {
      request.post("/news/getNewsByNewsType","国内新闻").then(res =>{
        for (let i=0;i<res.data.length;i++) {
          res.data[i].date = moment(res.data[i].date).format("YYYY-MM-DD")
        }
        this.tableDataForChinaNews = res.data
      })
    },
    //加载江苏新闻
    loadDataForHenanNews() {
      request.post("/news/getNewsByNewsType","江苏新闻").then(res =>{
        for (let i=0;i<res.data.length;i++) {
          res.data[i].date = moment(res.data[i].date).format("YYYY-MM-DD")
        }
        this.tableDataForHenanNews = res.data
      })
    },
    //加载扬州大事
    loadDataForZZNews() {
      request.post("/news/getNewsByNewsType","扬州大事").then(res =>{
        for (let i=0;i<res.data.length;i++) {
          res.data[i].date = moment(res.data[i].date).format("YYYY-MM-DD")
        }
        this.tableDataForZZNews = res.data
      })
    },
  },
  created() {
    document.body.style.overflow = 'auto'
    this.loadDataForGlobalNews()
    this.loadDataForChinaNews()
    this.loadDataForHenanNews()
    this.loadDataForZZNews()
  }
}
</script>

<style scoped>
.el-table tbody tr:hover>td {
  background-color:#ffffff!important
}
.divF{
  position: absolute;width: 100%;height: 45px;margin-top: 50.5px;background: white;box-shadow: 0 0 3px 0 #f3f3f3;
}
.l1{
  position: absolute;transform: translate(-50%,-50%);left: 21%;top: 50%;font-size: 17px;color:#409eff;
}
.l2{
  position: absolute;transform: translate(-50%,-50%);left: 49.5%;top: 50%;color: black;font-size: 17px;
}
.l3{
  position: absolute;transform: translate(-50%,-50%);left: 78%;top: 50%;color: black;font-size: 17px;
}
.divS{
  background: white;width: 60%;height: auto;position: absolute;left: 20%;margin-top: 120px;box-shadow: 0 0 3px 3px #f3f3f3;
}
.divT{
  width: 100%;height: 30px;text-align: center
}
.sp1{
  font-size: 20px;position:relative;top: 10px;color:#409eff;cursor:pointer;
}
.el1{
  width: 100%
}
.tr1{
  height: 25px;
}
.td1{
  text-align: left;padding-left: 10px;
}
.sp2{
  cursor:pointer;text-decoration: none;font-size: 14px;color: black;
}
.td2{
  width: 100px
}
.divF2{
  background: none;width: 100%;height: 100px;position: absolute;left: 0;bottom: 0
}
.a1{
  text-decoration: none;color: black;position: absolute;transform: translate(-50%,-50%);top: 50%;left: 50%;font-size: 15px;
}
</style>