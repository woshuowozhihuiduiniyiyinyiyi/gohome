(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-49d4"],{ZwYD:function(t,e,r){},eGdN:function(t,e,r){"use strict";var a=r("ZwYD");r.n(a).a},kP7L:function(t,e,r){"use strict";r.r(e);var a=r("6ZY3"),o=r.n(a),i=r("Nlzp"),s={data:function(){return{itemInfos:"",numberState:0,homeData:{},ticketOption:[],userOption:[],statusOption:[{status:"",label:"请选择"},{status:1,label:"抢票中"},{status:2,label:"交易成功"},{status:3,label:"交易关闭"}],total:null,listLoading:!1,submitLogining:!1,dialogOption:{title:"新增",status:!1},rules:{origin:[{required:!0,message:"请输入出发地",trigger:"change"}],destination:[{required:!0,message:"请输入目的地",trigger:"change"}],trainNumber:[{required:!0,message:"请输入车次",trigger:"change"}],seat:[{required:!0,message:"请输入座位",trigger:"change"}],price:[{required:!0,message:"请输入抢票服务费",trigger:"change"}]},Backup:{passengerList:[{name:"",idCard:""}]},orderListForm:{id:"",ownerInfo:{wxAccount:"",wxNickname:"",phone:""},origin:"",destination:"",departureDate:"",trainNumber:"",seat:"",price:"",robbingPrice:"",expectDate:"",expectDateArr:[],passengerList:[{name:"",idCard:""}]},multipleSelection:[],orderIdList:[],orderList:[],NumberValue:0,form:{},addFormVisible:!1,param:{data:{},page:{page:1,size:10},sid:this.$store.getters.accountToken}}},mounted:function(){this.getOrderData(),this.getOrderList(),this.getUserList(),this.getTicketUserLIist()},methods:{aaa:function(){console.log("a"),this.itemInfos="testing";var t=new this.clipboard(".btn");t.on("success",function(t){console.info("Action:",t.action),console.info("Text:",t.text),console.info("Trigger:",t.trigger),t.clearSelection()}),t.on("error",function(t){console.error("Action:",t.action),console.error("Trigger:",t.trigger)})},copyInfo:function(t){var e=this;this.itemInfos=t.expectDate+",  "+t.origin+"-"+t.destination+",  "+t.trainNumber+",  "+t.seat+"\r\n";for(var r=0;r<t.passengerList.length;r++)this.itemInfos+=t.passengerList[r].name+","+t.passengerList[r].idCard+"\r\n";this.itemInfos+="共"+t.passengerList.length+"人\r\n春节票服务费："+t.price/t.passengerList.length+"元/人   共"+t.price+"元\r\n先抢票后付款";var a=new this.clipboard(".copyInfo",{text:function(){return e.itemInfos}});a.on("success",function(){e.$message({message:"复制成功！",type:"success"})}),a.on("error",function(){e.$message({message:"复制失败，请手动选择复制！",type:"error"})})},removeDomain:function(t){var e=this.orderListForm.passengerList.indexOf(t);-1!==e&&this.orderListForm.passengerList.splice(e,1)},addDomain:function(){this.orderListForm.passengerList.push({name:"",idCard:""})},batch:function(){var t=this;this.param.data={},this.$confirm("确定删除任务?","Prompt",{confirmButtonText:"Yes",cancelButtonText:"No",type:"warning"}).then(function(){t.param.data.orderIdList=t.orderIdList,i.a.orderApi.deleteOrder(t.param).then(function(e){t.getOrderList()})}).catch(function(){})},handleSelectionChange:function(t){this.multipleSelection=t,this.orderIdList=this.multipleSelection.map(function(t){return t.id})},cleardata:function(){this.form.queryText=""},handleEdit:function(t,e){var r=this;this.numberState=0,this.param.data.id=e.id,i.a.orderApi.getOrderDetail(this.param).then(function(t){var e=t.data.data;r.orderListForm=e,r.orderListForm.expectDateArr=e.expectDate.split("、")}),this.addFormVisible=!0,this.dialogOption.title="查看详情"},onsavaorderList:function(t){var e=this,r=o()([],this.orderListForm.expectDateArr);this.orderListForm.expectDate=r.join(",").replace(/,/g,"、"),this.param.data=this.orderListForm,this.submitLogining=!0,console.log(this.param.data),this.$refs[t].validate(function(t){if(!t)return console.log("error submit!!"),!1;i.a.orderApi.orderSave(e.param).then(function(t){e.addFormVisible=!1,e.orderListForm.passengerList=e.Backup.passengerList,e.$notify({title:"成功",message:t.data.state.msg,type:"success"}),e.submitLogining=!1,e.getOrderList()})})},handleCurrentChange:function(t){this.param.page.page=t,this.param.data[this.form.queryParam]=this.form.queryText,this.getOrderList()},handleClose:function(t){this.$refs[t].resetFields(),this.orderListForm.id="",this.orderListForm.ownerInfo.wxAccount=null,this.orderListForm.ownerInfo.wxNickname=null,this.orderListForm.ownerInfo.phone=null,this.orderListForm.passengerList=this.Backup.passengerList,this.addFormVisible=!1},handleAdd:function(){this.numberState=1,this.dialogOption.title="新增",this.addFormVisible=!0},getInfo:function(){this.param.data={},this.param.page.page=1,this.param.data=this.form,this.getOrderList()},getOrderData:function(){var t=this;i.a.orderApi.getOrderData(this.param).then(function(e){t.homeData=e.data.data})},getTicketUserLIist:function(){var t=this;i.a.orderApi.ticketUserList(this.param).then(function(e){t.ticketOption=e.data.data})},getUserList:function(){var t=this;i.a.orderApi.getUserList(this.param).then(function(e){t.userOption=e.data.data})},getOrderList:function(){var t=this;this.listLoading=!0,i.a.orderApi.getOrderList(this.param).then(function(e){t.orderList=e.data.data,t.total=e.data.page.total,t.listLoading=!1})}}},n=(r("eGdN"),r("ZrdR")),l=Object(n.a)(s,function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("section",{staticStyle:{padding:"20px"}},[r("textarea",{directives:[{name:"model",rawName:"v-model",value:t.itemInfos,expression:"itemInfos"}],attrs:{id:"foo"},domProps:{value:t.itemInfos},on:{input:function(e){e.target.composing||(t.itemInfos=e.target.value)}}}),t._v(" "),r("el-col",{staticClass:"toolbar",staticStyle:{"padding-bottom":"0"},attrs:{span:24}},[r("el-form",{ref:"form",attrs:{inline:!0}},[r("el-form-item",{attrs:{label:"微信昵称"}},[r("el-input",{attrs:{placeholder:"请输入微信昵称"},model:{value:t.form.ownerWxNickName,callback:function(e){t.$set(t.form,"ownerWxNickName",e)},expression:"form.ownerWxNickName"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"姓名"}},[r("el-input",{attrs:{placeholder:"请输入乘客姓名"},model:{value:t.form.passengerName,callback:function(e){t.$set(t.form,"passengerName",e)},expression:"form.passengerName"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"身份证号"}},[r("el-input",{attrs:{placeholder:"请输入乘客身份证号"},model:{value:t.form.passengerIdCard,callback:function(e){t.$set(t.form,"passengerIdCard",e)},expression:"form.passengerIdCard"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"抢票人员"}},[r("el-input",{attrs:{placeholder:"请输入抢票人员"},model:{value:t.form.robbingUserName,callback:function(e){t.$set(t.form,"robbingUserName",e)},expression:"form.robbingUserName"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"状态"}},[r("el-select",{model:{value:t.form.status,callback:function(e){t.$set(t.form,"status",e)},expression:"form.status"}},[r("el-option",{attrs:{label:"全部",value:""}}),t._v(" "),r("el-option",{attrs:{label:"抢票中",value:"1"}}),t._v(" "),r("el-option",{attrs:{label:"交易成功",value:"2"}}),t._v(" "),r("el-option",{attrs:{label:"交易关闭",value:"3"}})],1)],1),t._v(" "),r("el-form-item",{attrs:{label:"创建日期"}},[r("el-date-picker",{staticStyle:{width:"200px"},attrs:{type:"datetime",placeholder:"选择日期时间"},model:{value:t.form.createDateMin,callback:function(e){t.$set(t.form,"createDateMin",e)},expression:"form.createDateMin"}}),t._v(" "),r("span",[t._v("-")]),t._v(" "),r("el-date-picker",{staticStyle:{width:"200px"},attrs:{type:"datetime",placeholder:"选择日期时间"},model:{value:t.form.createDateMax,callback:function(e){t.$set(t.form,"createDateMax",e)},expression:"form.createDateMax"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"抢票时间"}},[r("el-date-picker",{staticStyle:{width:"200px"},attrs:{type:"datetime",placeholder:"选择日期时间",timestamp:""},model:{value:t.form.departureDateMin,callback:function(e){t.$set(t.form,"departureDateMin",e)},expression:"form.departureDateMin"}}),t._v(" "),r("span",[t._v("-")]),t._v(" "),r("el-date-picker",{staticStyle:{width:"200px"},attrs:{type:"datetime",timestamp:"",placeholder:"选择日期时间"},model:{value:t.form.departureDateMax,callback:function(e){t.$set(t.form,"departureDateMax",e)},expression:"form.departureDateMax"}})],1),t._v(" "),r("el-form-item",[r("el-button",{attrs:{type:"primary"},on:{click:t.getInfo}},[t._v("查询")])],1),t._v(" "),r("el-form-item")],1)],1),t._v(" "),r("el-col",[r("el-col",{attrs:{span:6}},[t._v("总订单条数 ："),r("span",{staticStyle:{color:"red"}},[t._v(t._s(t.homeData.totalOrderCount))])]),t._v(" "),r("el-col",{attrs:{span:6}},[t._v("抢票中："),r("span",{staticStyle:{color:"red"}},[t._v(t._s(t.homeData.robbingCount))])]),t._v(" "),r("el-col",{attrs:{span:6}},[t._v("交易成功："),r("span",{staticStyle:{color:"red"}},[t._v(t._s(t.homeData.successCount))])]),t._v(" "),r("el-col",{attrs:{span:6}},[t._v("总收益："),r("span",{staticStyle:{color:"red"}},[t._v(t._s(t.homeData.totalProfit))])])],1),t._v(" "),r("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],staticStyle:{width:"100%"},attrs:{data:t.orderList,"highlight-current-row":""},on:{"selection-change":t.handleSelectionChange}},[r("el-table-column",{attrs:{type:"selection",width:"40"}}),t._v(" "),r("el-table-column",{attrs:{label:"微信昵称",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[r("span",[t._v(t._s(e.row.ownerInfo.wxNickname))])]}}])}),t._v(" "),r("el-table-column",{attrs:{prop:"expectDate",label:"用户期待的出发时间",width:"160"}}),t._v(" "),r("el-table-column",{attrs:{prop:"origin",label:"出发地",width:"120"}}),t._v(" "),r("el-table-column",{attrs:{prop:"destination",label:"目的地",width:"120"}}),t._v(" "),r("el-table-column",{attrs:{prop:"trainNumber",label:"车次",width:"200"}}),t._v(" "),r("el-table-column",{attrs:{prop:"seat",label:"座位",width:"140"}}),t._v(" "),r("el-table-column",{attrs:{label:"姓名",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.passengerList[0]?r("span",t._l(e.row.passengerList,function(e,a){return r("span",{key:a},[t._v("\n              "+t._s(e.name)+"、\n            ")])})):r("span")]}}])}),t._v(" "),r("el-table-column",{attrs:{label:"身份证号",width:"180"},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.passengerList[0]?r("span",t._l(e.row.passengerList,function(e,a){return r("span",{key:a},[t._v("\n              "+t._s(e.idCard)+"、\n            ")])})):r("span")]}}])}),t._v(" "),r("el-table-column",{attrs:{prop:"price",label:"抢票服务费",width:"80"}}),t._v(" "),r("el-table-column",{attrs:{prop:"profit",label:"收益",width:"80"}}),t._v(" "),r("el-table-column",{attrs:{prop:"robbingTicketUserName",label:"抢票人员",width:"120"}}),t._v(" "),r("el-table-column",{attrs:{prop:"statusStr",label:"状态",width:"80"}}),t._v(" "),r("el-table-column",{attrs:{prop:"createdAtStr",label:"创建时间",width:"180"}}),t._v(" "),r("el-table-column",{attrs:{label:"操作","min-width":"180"},scopedSlots:t._u([{key:"default",fn:function(e){return[r("el-button",{staticClass:"copyInfo",attrs:{size:"small","data-clipboard-target":"#foo"},on:{click:function(r){t.copyInfo(e.row)}}},[t._v("复制")]),t._v(" "),1===e.row.status?r("el-button",{attrs:{size:"small"},on:{click:function(r){t.handleEdit(e.$index,e.row)}}},[t._v("编辑")]):r("el-button",{attrs:{size:"small"},on:{click:function(r){t.handleEdit(e.$index,e.row)}}},[t._v("查看详情")])]}}])})],1),t._v(" "),r("el-col",{staticClass:"toolbar",staticStyle:{margin:"20px"},attrs:{span:24}},[r("el-button",{attrs:{disabled:0===t.multipleSelection.length,type:"warning"},on:{click:t.batch}},[t._v("批量删除")]),t._v(" "),r("el-pagination",{staticStyle:{float:"right"},attrs:{"page-size":t.param.page.size,total:t.total,layout:"prev, pager, next"},on:{"current-change":t.handleCurrentChange}})],1),t._v(" "),r("el-dialog",{attrs:{visible:t.addFormVisible,"close-on-click-modal":!1},on:{"update:visible":function(e){t.addFormVisible=e},close:function(e){t.handleClose("orderListForm")}}},[r("div",{staticClass:"el-dialog__title",staticStyle:{margin:"-30px 0 20px 0"}},[t._v(t._s(t.dialogOption.title))]),t._v(" "),r("el-form",{ref:"orderListForm",staticClass:"active",attrs:{"label-position":"left",rules:t.rules,model:t.orderListForm,"label-width":"120px"}},[r("el-form-item",{staticStyle:{display:"none"},model:{value:t.orderListForm.id,callback:function(e){t.$set(t.orderListForm,"id",e)},expression:"orderListForm.id"}}),t._v(" "),r("el-form-item",{attrs:{label:"微信号",prop:"ownerInfo.wxAccount"}},[r("el-input",{attrs:{disabled:"","auto-complete":"off"},model:{value:t.orderListForm.ownerInfo.wxAccount,callback:function(e){t.$set(t.orderListForm.ownerInfo,"wxAccount",e)},expression:"orderListForm.ownerInfo.wxAccount"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"微信昵称",prop:"ownerInfo.wxNickname"}},[r("el-input",{attrs:{disabled:"","auto-complete":"off"},model:{value:t.orderListForm.ownerInfo.wxNickname,callback:function(e){t.$set(t.orderListForm.ownerInfo,"wxNickname",e)},expression:"orderListForm.ownerInfo.wxNickname"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"手机号",prop:"phone"}},[r("el-input",{attrs:{disabled:1!==t.numberState&&2==t.orderListForm.status||1!==t.numberState&&3==t.orderListForm.status,"auto-complete":"off"},model:{value:t.orderListForm.ownerInfo.phone,callback:function(e){t.$set(t.orderListForm.ownerInfo,"phone",e)},expression:"orderListForm.ownerInfo.phone"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"出发地",prop:"origin"}},[r("el-input",{attrs:{disabled:1!==t.numberState&&2==t.orderListForm.status||1!==t.numberState&&3==t.orderListForm.status,"auto-complete":"off"},model:{value:t.orderListForm.origin,callback:function(e){t.$set(t.orderListForm,"origin",e)},expression:"orderListForm.origin"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"目的地",prop:"destination"}},[r("el-input",{attrs:{disabled:1!==t.numberState&&2==t.orderListForm.status||1!==t.numberState&&3==t.orderListForm.status,"auto-complete":"off"},model:{value:t.orderListForm.destination,callback:function(e){t.$set(t.orderListForm,"destination",e)},expression:"orderListForm.destination"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"出发日期",prop:"departureDate"}},[r("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"datetime",placeholder:"选择日期时间",disabled:1!==t.numberState&&2==t.orderListForm.status||1!==t.numberState&&3==t.orderListForm.status},model:{value:t.orderListForm.departureDate,callback:function(e){t.$set(t.orderListForm,"departureDate",e)},expression:"orderListForm.departureDate"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"用户期待的出发时间",prop:"expectDateArr"}},[r("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"dates",placeholder:"选择一个或多个日期","value-format":"yyyy-MM-dd","auto-complete":"off"},model:{value:t.orderListForm.expectDateArr,callback:function(e){t.$set(t.orderListForm,"expectDateArr",e)},expression:"orderListForm.expectDateArr"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"车次",prop:"trainNumber"}},[r("el-input",{attrs:{disabled:1!==t.numberState&&2==t.orderListForm.status||1!==t.numberState&&3==t.orderListForm.status,"auto-complete":"off"},model:{value:t.orderListForm.trainNumber,callback:function(e){t.$set(t.orderListForm,"trainNumber",e)},expression:"orderListForm.trainNumber"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"座位",prop:"seat"}},[r("el-input",{attrs:{disabled:1!==t.numberState&&2==t.orderListForm.status||1!==t.numberState&&3==t.orderListForm.status,"auto-complete":"off"},model:{value:t.orderListForm.seat,callback:function(e){t.$set(t.orderListForm,"seat",e)},expression:"orderListForm.seat"}})],1),t._v(" "),t._l(t.orderListForm.passengerList,function(e,a){return r("el-form-item",{key:a,attrs:{label:"乘客信息"}},[r("el-input",{attrs:{disabled:1!==t.numberState&&2==t.orderListForm.status||1!==t.numberState&&3==t.orderListForm.status},model:{value:e.name,callback:function(r){t.$set(e,"name",r)},expression:"domain.name"}}),r("el-input",{attrs:{disabled:1!==t.numberState&&2==t.orderListForm.status||1!==t.numberState&&3==t.orderListForm.status},model:{value:e.idCard,callback:function(r){t.$set(e,"idCard",r)},expression:"domain.idCard"}}),t._v(" "),r("el-button",{on:{click:t.addDomain}},[t._v("+")]),t._v(" "),r("el-button",{on:{click:function(r){r.preventDefault(),t.removeDomain(e)}}},[t._v("-")])],1)}),t._v(" "),r("el-form-item",{attrs:{label:"抢票服务费",prop:"price"}},[r("el-input",{attrs:{disabled:1!==t.numberState&&2==t.orderListForm.status||1!==t.numberState&&3==t.orderListForm.status,"auto-complete":"off"},model:{value:t.orderListForm.price,callback:function(e){t.$set(t.orderListForm,"price",e)},expression:"orderListForm.price"}})],1),t._v(" "),r("el-form-item",{attrs:{label:"接单人员",prop:"portalUserId"}},[r("el-select",{staticStyle:{width:"100%"},attrs:{disabled:1!==t.numberState&&2==t.orderListForm.status||1!==t.numberState&&3==t.orderListForm.status},model:{value:t.orderListForm.portalUserId,callback:function(e){t.$set(t.orderListForm,"portalUserId",e)},expression:"orderListForm.portalUserId"}},t._l(t.userOption,function(t,e){return r("el-option",{key:e,attrs:{label:t.name,value:t.id}})}))],1),t._v(" "),r("el-form-item",{attrs:{label:"状态",prop:"status"}},[r("el-select",{staticStyle:{width:"100%"},model:{value:t.orderListForm.status,callback:function(e){t.$set(t.orderListForm,"status",e)},expression:"orderListForm.status"}},t._l(t.statusOption,function(t,e){return r("el-option",{key:e,attrs:{label:t.label,value:t.status}})}))],1),t._v(" "),r("el-form-item",{attrs:{label:"抢票人员",prop:"robbingTicketUserId"}},[r("el-select",{staticStyle:{width:"100%"},model:{value:t.orderListForm.robbingTicketUserId,callback:function(e){t.$set(t.orderListForm,"robbingTicketUserId",e)},expression:"orderListForm.robbingTicketUserId"}},t._l(t.ticketOption,function(t,e){return r("el-option",{key:e,attrs:{label:t.name,value:t.id}})}))],1),t._v(" "),r("el-form-item",{attrs:{label:"抢票价格",prop:"robbingPrice"}},[r("el-input",{attrs:{"auto-complete":"off"},model:{value:t.orderListForm.robbingPrice,callback:function(e){t.$set(t.orderListForm,"robbingPrice",e)},expression:"orderListForm.robbingPrice"}})],1),t._v(" "),r("el-form-item",{attrs:{"label-width":"120px"}},[r("el-button",{attrs:{loading:t.submitLogining,type:"primary"},on:{click:function(e){t.onsavaorderList("orderListForm")}}},[t._v("保存")])],1)],2)],1)],1)},[],!1,null,null,null);l.options.__file="index.vue";e.default=l.exports}}]);