(function(t){function e(e){for(var a,i,s=e[0],l=e[1],c=e[2],d=0,m=[];d<s.length;d++)i=s[d],n[i]&&m.push(n[i][0]),n[i]=0;for(a in l)Object.prototype.hasOwnProperty.call(l,a)&&(t[a]=l[a]);u&&u(e);while(m.length)m.shift()();return r.push.apply(r,c||[]),o()}function o(){for(var t,e=0;e<r.length;e++){for(var o=r[e],a=!0,s=1;s<o.length;s++){var l=o[s];0!==n[l]&&(a=!1)}a&&(r.splice(e--,1),t=i(i.s=o[0]))}return t}var a={},n={app:0},r=[];function i(e){if(a[e])return a[e].exports;var o=a[e]={i:e,l:!1,exports:{}};return t[e].call(o.exports,o,o.exports,i),o.l=!0,o.exports}i.m=t,i.c=a,i.d=function(t,e,o){i.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:o})},i.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},i.t=function(t,e){if(1&e&&(t=i(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var o=Object.create(null);if(i.r(o),Object.defineProperty(o,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var a in t)i.d(o,a,function(e){return t[e]}.bind(null,a));return o},i.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return i.d(e,"a",e),e},i.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},i.p="/";var s=window["webpackJsonp"]=window["webpackJsonp"]||[],l=s.push.bind(s);s.push=e,s=s.slice();for(var c=0;c<s.length;c++)e(s[c]);var u=l;r.push([0,"chunk-vendors"]),o()})({0:function(t,e,o){t.exports=o("56d7")},"1e91":function(t,e){},"56d7":function(t,e,o){"use strict";o.r(e);o("cadf"),o("551c"),o("f751"),o("097d");var a=o("2b0e"),n=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("v-app",[o("NavigationBar"),o("v-content",[o("router-view")],1)],1)},r=[],i=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{attrs:{id:"app"}},[o("v-navigation-drawer",{attrs:{app:""},model:{value:t.drawer,callback:function(e){t.drawer=e},expression:"drawer"}},[o("v-list",{attrs:{dense:""}},[o("v-list-item",{attrs:{router:"",to:{name:"Home"}}},[o("v-list-item-action",[o("v-icon",[t._v("mdi-home")])],1),o("v-list-item-content",[o("v-list-item-title",[t._v("Home")])],1)],1),o("v-list-item",{attrs:{router:"",to:{name:"BookList"}}},[o("v-list-item-action",[o("v-icon",[t._v("mdi-library")])],1),o("v-list-item-content",[o("v-list-item-title",[t._v("Book list")])],1)],1)],1)],1),o("v-app-bar",{attrs:{app:"",color:"indigo",dark:""}},[o("v-app-bar-nav-icon",{on:{click:function(e){e.stopPropagation(),t.drawer=!t.drawer}}}),o("v-toolbar-title",{staticClass:"headline"},[t._v("Library")]),o("v-layout",{attrs:{"align-start":"","justify-end":"",wrap:""}},[o("v-flex",{attrs:{xs6:"",md2:"","order-md3":"","order-sm2":""}},[o("PopupAddBookItem")],1),o("v-flex",{attrs:{xs6:"",md2:"","order-md2":"","order-sm1":""}},[o("PopupAddAuthorItem")],1)],1)],1)],1)},s=[],l=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("v-container",{attrs:{"grid-list-md":"","text-center":""}},[o("v-layout",{attrs:{"align-center":"","justify-end":"",row:"","fill-height":""}},[o("v-dialog",{attrs:{persistent:"","max-width":"600px"},scopedSlots:t._u([{key:"activator",fn:function(e){var a=e.on;return[o("v-btn",t._g({attrs:{color:"primary",dark:""},on:{click:t.onBookAddBtnClick}},a),[t._v("Add book")])]}}]),model:{value:t.dialog,callback:function(e){t.dialog=e},expression:"dialog"}},[o("v-card",[o("v-card-title",[o("span",{staticClass:"headline"},[t._v("Book information")])]),o("v-card-text",[o("v-container",{attrs:{"grid-list-md":""}},[o("v-layout",{attrs:{wrap:""}},[o("v-flex",{attrs:{xs12:""}},[o("v-text-field",{attrs:{label:"Book title*",required:""},model:{value:t.title,callback:function(e){t.title=e},expression:"title"}})],1),o("v-flex",{attrs:{xs12:"",sm6:""}},[o("v-autocomplete",{attrs:{items:t.authors,"item-value":"id","item-text":"name",label:"Authors",multiple:""},model:{value:t.selectedAuthors,callback:function(e){t.selectedAuthors=e},expression:"selectedAuthors"}})],1),o("v-flex",{attrs:{xs12:"",sm6:""}},[o("v-autocomplete",{attrs:{items:t.genres,"item-value":"genreName","item-text":"genreName",label:"Genre"},model:{value:t.selectedGenre,callback:function(e){t.selectedGenre=e},expression:"selectedGenre"}})],1)],1)],1),o("small",[t._v("*indicates required field")])],1),o("v-card-actions",[o("v-spacer"),o("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:[function(e){t.dialog=!1},t.submitBook]}},[t._v("Save")])],1)],1)],1)],1)],1)},c=[],u=o("bc3a"),d=o.n(u),m=d.a.create({baseURL:"/api/v1"}),v={getBooks:function(){return m.get("/books")},getAuthors:function(){return m.get("/authors")},getGenres:function(){return m.get("/genres")},getBook:function(t){return m.get("/books/"+t)},deleteBook:function(t){return m.delete("/books/"+t+"/delete")},getComments:function(t){return m.get("/books/"+t+"/comments")},addAuthor:function(t){return m.post("/authors",{name:t})},addComment:function(t,e){return m.post("/books/"+e+"/comments",{commentDescription:t})},addBook:function(t,e,o){return m.post("/books/",{title:t,authors:e,genre:o})}},f={name:"PopupAddBookItem",data:function(){return{dialog:!1,title:"",authors:[],selectedAuthors:[],genres:[],selectedGenre:[]}},methods:{fillGenresList:function(){var t=this;return v.getGenres().then(function(e){t.genres=e.data})},fillAuthorsList:function(){var t=this;return v.getAuthors().then(function(e){t.authors=e.data})},onBookAddBtnClick:function(){this.fillAuthorsList(),this.fillGenresList()},submitBook:function(){var t=this,e=this.title,o=this.selectedAuthors,a=this.selectedGenre;return v.addBook(e,o,a).then(function(){t.$root.$emit("bookSubmittedEvent")})}}},p=f,h=o("2877"),b=o("6544"),k=o.n(b),x=o("c6a6"),g=o("8336"),V=o("b0af"),_=o("99d9"),C=o("a523"),y=o("169a"),A=o("0e8f"),w=o("a722"),B=o("2fa4"),L=o("8654"),I=Object(h["a"])(p,l,c,!1,null,"09cda9a0",null),j=I.exports;k()(I,{VAutocomplete:x["a"],VBtn:g["a"],VCard:V["a"],VCardActions:_["a"],VCardText:_["b"],VCardTitle:_["c"],VContainer:C["a"],VDialog:y["a"],VFlex:A["a"],VLayout:w["a"],VSpacer:B["a"],VTextField:L["a"]});var S=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("v-container",{attrs:{"grid-list-md":"","text-center":""}},[o("v-layout",{attrs:{"align-center":"","justify-end":"",row:"","fill-height":""}},[o("v-dialog",{attrs:{persistent:"","max-width":"600px"},scopedSlots:t._u([{key:"activator",fn:function(e){var a=e.on;return[o("v-btn",t._g({attrs:{color:"primary",dark:""}},a),[t._v("Add Author")])]}}]),model:{value:t.dialog,callback:function(e){t.dialog=e},expression:"dialog"}},[o("v-card",[o("v-card-title",[o("span",{staticClass:"headline"},[t._v("Author information")])]),o("v-card-text",[o("v-container",{attrs:{"grid-list-md":""}},[o("v-layout",{attrs:{wrap:""}},[o("v-flex",{attrs:{xs12:""}},[o("v-text-field",{attrs:{label:"Author's name*",required:"",clearable:""},model:{value:t.author,callback:function(e){t.author=e},expression:"author"}})],1)],1)],1),o("small",[t._v("*indicates required field")])],1),o("v-card-actions",[o("v-spacer"),o("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:[function(e){t.dialog=!1},t.clearAuthorName]}},[t._v("Close")]),o("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:[function(e){t.dialog=!1},t.submitAuthor]}},[t._v("Save")])],1)],1)],1)],1)],1)},O=[],T={name:"PopupAddAuthorItem",data:function(){return{dialog:!1,author:""}},methods:{submitAuthor:function(){var t=this.author;v.addAuthor(t),this.clearAuthorName()},clearAuthorName:function(){this.author=""}}},$=T,N=Object(h["a"])($,S,O,!1,null,"70a618c8",null),P=N.exports;k()(N,{VBtn:g["a"],VCard:V["a"],VCardActions:_["a"],VCardText:_["b"],VCardTitle:_["c"],VContainer:C["a"],VDialog:y["a"],VFlex:A["a"],VLayout:w["a"],VSpacer:B["a"],VTextField:L["a"]});var E={name:"AppHeader",components:{PopupAddAuthorItem:P,PopupAddBookItem:j},data:function(){return{drawer:!0}}},F=E,G=o("40dc"),D=o("5bc1"),q=o("132d"),H=o("8860"),M=o("da13"),R=o("1800"),J=o("5d23"),U=o("f774"),z=o("2a7f"),K=Object(h["a"])(F,i,s,!1,null,"5464c9f0",null),Q=K.exports;k()(K,{VAppBar:G["a"],VAppBarNavIcon:D["a"],VFlex:A["a"],VIcon:q["a"],VLayout:w["a"],VList:H["a"],VListItem:M["a"],VListItemAction:R["a"],VListItemContent:J["a"],VListItemTitle:J["b"],VNavigationDrawer:U["a"],VToolbarTitle:z["a"]});var W={components:{NavigationBar:Q},name:"App"},X=W,Y=o("7496"),Z=o("a75b"),tt=Object(h["a"])(X,n,r,!1,null,null,null),et=tt.exports;k()(tt,{VApp:Y["a"],VContent:Z["a"]});var ot=o("8c4f"),at=o("57da"),nt=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("v-container",{attrs:{"grid-list-md":""}},[o("v-layout",{attrs:{row:"",wrap:""}},t._l(t.books,function(e){return o("v-flex",{key:e.id,attrs:{xs12:"",sm10:"",md8:"","offset-sm1":"","offset-md-2":""}},[o("v-card",{staticClass:"white--text",attrs:{color:"indigo"}},[o("v-container",{attrs:{fluid:""}},[o("v-layout",{attrs:{row:""}},[o("v-flex",{attrs:{xs8:"",md9:""}},[o("v-card-title",[o("div",[o("div",{staticClass:"headline"},[t._v(t._s(e.title))])])]),o("v-card-actions",[o("v-btn",{staticClass:"primary",attrs:{router:"",to:{name:"BookItem",params:{id:e.id}}}},[t._v("\n                                    Open\n                                ")])],1)],1)],1)],1)],1)],1)}),1)],1)},rt=[],it={name:"BookList",data:function(){return{books:this.showBooks(),errors:""}},methods:{showBooks:function(){var t=this;return v.getBooks().then(function(e){t.books=e.data}).catch(function(e){t.errors=e})}},mounted:function(){var t=this;this.$root.$on("bookSubmittedEvent",function(){t.showBooks()})}},st=it,lt=Object(h["a"])(st,nt,rt,!1,null,"eaec5b7a",null),ct=lt.exports;k()(lt,{VBtn:g["a"],VCard:V["a"],VCardActions:_["a"],VCardTitle:_["c"],VContainer:C["a"],VFlex:A["a"],VLayout:w["a"]});var ut=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("v-container",{attrs:{"grid-list-md":""}},[o("h1",{staticClass:"subheading grey--text"},[t._v("Book Card")]),o("v-layout",{attrs:{row:"",wrap:""}},[o("v-flex",{attrs:{xs12:"",md6:"","offset-sm-1":"","offset-md-2":""}},[o("div",{staticClass:"caption grey--text"},[t._v("Title")]),o("div",[t._v(t._s(t.book.title))])]),o("v-flex",{attrs:{xs12:"",md6:"","offset-sm-1":"","offset-md-2":""}},[o("div",{staticClass:"caption grey--text"},[t._v("Author(s)")]),o("v-list",{staticClass:"caption lighten-5 grey"},t._l(t.book.authors,function(e){return o("v-list-item",{key:e.id},[o("div",{staticClass:"headline"},[t._v(t._s(e.name))])])}),1)],1),o("v-flex",{attrs:{xs12:"",md6:"","offset-sm-1":"","offset-md-2":""}},[o("div",{staticClass:"caption grey--text"},[t._v("Genre")]),o("div",[t.book.genre?o("span",[t._v("\n                    "+t._s(t.book.genre.genreName)+"\n                ")]):t._e()])]),o("v-flex",[o("v-btn",{staticClass:"red mx0 mt-3",on:{click:function(e){return t.deleteCurrentBook()}}},[t._v("Delete")])],1)],1),o("v-spacer"),o("CommentList",{attrs:{"book-id":t.book.id}})],1)},dt=[],mt=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("v-container",[o("v-layout",[o("v-list",{staticClass:"caption lighten-5 grey"},t._l(t.comments,function(e){return o("v-list-item",{key:e.id,staticClass:"item"},[o("v-flex",{attrs:{xs12:"",md6:"","offset-sm-1":"","offset-md-2":""}},[o("div",{staticClass:"headline"},[t._v(t._s(e.commentDescription))])])],1)}),1),o("v-spacer")],1),o("CommentAddForm",{attrs:{"book-id":t.bookId},on:{commentSubmitted:t.commentSubmittedResponse}})],1)},vt=[],ft=(o("c5f6"),function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("v-container",{attrs:{"grid-list-md":""}},[o("v-layout",{attrs:{"align-center":"","justify-space-around":"",row:"","fill-height":""}},[o("v-card",[o("v-card-title",[o("h2",[t._v("Add New Comment")])]),o("v-card-text",[o("v-form",{staticClass:"px-3"},[o("v-textarea",{attrs:{label:"Enter your comment",maxlength:"140"},model:{value:t.comment,callback:function(e){t.comment=e},expression:"comment"}}),o("v-spacer"),o("v-btn",{staticClass:"success mx0 mt-3",on:{click:function(e){return t.submitComment()}}},[t._v("Submit comment")])],1)],1)],1)],1)],1)}),pt=[],ht={name:"CommentAddForm",props:{bookId:Number},data:function(){return{comment:""}},methods:{submitComment:function(){var t=this,e=this.comment,o=this.bookId;return v.addComment(e,o).then(function(){t.$emit("commentSubmitted")})}}},bt=ht,kt=o("4bd4"),xt=o("a844"),gt=Object(h["a"])(bt,ft,pt,!1,null,"cb17c188",null),Vt=gt.exports;k()(gt,{VBtn:g["a"],VCard:V["a"],VCardText:_["b"],VCardTitle:_["c"],VContainer:C["a"],VForm:kt["a"],VLayout:w["a"],VSpacer:B["a"],VTextarea:xt["a"]});var _t={name:"CommentList",components:{CommentAddForm:Vt},props:{bookId:Number},data:function(){return{comments:"",errors:""}},methods:{showComments:function(){var t=this;return v.getComments(this.bookId).then(function(e){t.comments=e.data}).catch(function(e){t.errors=e})},commentSubmittedResponse:function(){this.showComments()}},watch:{bookId:function(){this.showComments()}}},Ct=_t,yt=Object(h["a"])(Ct,mt,vt,!1,null,"1a8b76a8",null),At=yt.exports;k()(yt,{VContainer:C["a"],VFlex:A["a"],VLayout:w["a"],VList:H["a"],VListItem:M["a"],VSpacer:B["a"]});var wt={name:"BookItem",props:["id"],components:{CommentList:At},data:function(){return{book:this.showBookDetails(),errors:""}},methods:{showBookDetails:function(){var t=this;return v.getBook(this.id).then(function(e){t.book=e.data}).catch(function(e){t.errors=e})},deleteCurrentBook:function(){v.deleteBook(this.id),this.$router.push("/books")}}},Bt=wt,Lt=Object(h["a"])(Bt,ut,dt,!1,null,"115fbe83",null),It=Lt.exports;k()(Lt,{VBtn:g["a"],VContainer:C["a"],VFlex:A["a"],VLayout:w["a"],VList:H["a"],VListItem:M["a"],VSpacer:B["a"]}),a["a"].use(ot["a"]);var jt=new ot["a"]({mode:"history",routes:[{path:"/",name:"Home",component:at["default"]},{path:"/books",name:"BookList",component:ct},{path:"/books/:id",name:"BookItem",component:It,props:!0}]}),St=o("f309");a["a"].use(St["a"]);var Ot=new St["a"]({icons:{iconfont:"mdi"}});a["a"].config.productionTip=!1,new a["a"]({router:jt,vuetify:Ot,render:function(t){return t(et)}}).$mount("#app")},"57da":function(t,e,o){"use strict";var a=o("9523"),n=o("e732"),r=o("2877"),i=Object(r["a"])(n["default"],a["a"],a["b"],!1,null,null,null);e["default"]=i.exports},9523:function(t,e,o){"use strict";var a=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"home"})},n=[];o.d(e,"a",function(){return a}),o.d(e,"b",function(){return n})},e732:function(t,e,o){"use strict";var a=o("1e91"),n=o.n(a);e["default"]=n.a}});
//# sourceMappingURL=app.4f90cb53.js.map