<template>
  <div id="articleDetail">
    <div id="articleTile">
      <div style="font-weight: bold;font-size: 1.2rem;padding-top: 0.3rem">
        中国还有哪鲜为人知可以超越腾讯、阿里的公司仍未上市？
      </div>
      <div style="margin: 1.5rem 0 0.5rem 0;">
        <table style="width: 100%" cellpadding="0" cellspacing="0">
          <tr>
            <td rowspan="3" align="middle">
              <img src="../../assets/images/header.jpeg" style="width: 2.5rem;border-radius: 50%"/>
            </td>
          </tr>
          <tr>
            <td style="font-weight: bold;font-size: 0.9rem">
              上观
            </td>
            <td rowspan="2">
              <button class="focus-button"><i class="icon-plus" style="font-size: 0.7rem"></i> 关注</button>
            </td>
          </tr>
          <tr>
            <td style="font-size: 0.8rem;color: #888">
              2018-03-07 06:30:47
            </td>
          </tr>
        </table>
      </div>
    </div>
    <div id="articleContent">
      <div>
        在全国政协十三届一次会议上，施一公在参与讨论《政府工作报告》时发现，过去5年里，国内有效发明专利拥有量增加了两倍，技术交易额翻了一番。“如果较真一点，假设国内有效发明专利从100增加到300，技术交易额从100变成了200，那么单个专利交易额其实是不增反降了，是5年前的66%。”施一公算了一笔账。<br/><br/>
        这一数据折射出来的，是科技评价体系的问题。施一公称，在各个单位，不论是晋升还是考量绩效，都会把专利、发表文章、文章的引用数和文章所发表杂志的影响因子作为标准，而且这一风气愈演愈烈。<br/><br/>
        “但这几个核心的科技评价指标——文章数量、论文引用率、杂志的影响因子——都可以人为地提高。我想大家知道我这句话的意思。”<br/><br/>
        他表示，各个学校、单位都办了杂志，想要提高影响因子，互相引用就可以，引用多了，‘影响因子’自然也就高了。“中国这样的国家，想要把这3个指标做上去，我认为是易如反掌。”在他看来，论文不足以说明科技实力，美国没有这样的评价方式，科技实力却依然领先。“因此，论文和科技实力是两回事，大家千万要分开。”<br/><br/>
        不过他也承认，数字指标在各个单位都很重要，影响到评奖、评优、评先：“这是矛盾的两方面，一方面如果没有这些标准，如何反映科研的质量，但有了这些标准，大家拼命‘做’这个数字怎么办？”<br/><br/>
      </div>
      <div style="text-align: center;margin: 1.5rem 0;font-size: 0.8rem">
        <span class="zan-cai-num"
              :style="{borderColor: !zanContent ? '' : '#26a2ff',color: !zanContent ? '' : '#26a2ff'}">
          <i class="icon-point-up"></i>
          <span @click="getZanContent">888</span>
          </span>
        <span class="zan-cai-num"
              :style="{marginLeft: '10%', borderColor: !caiContent ? '' : '#26a2ff', color: !caiContent ? '' : '#26a2ff'}">
          <i class="icon-point-down"></i>
          <span @click="getCaiContent">668</span>
        </span>
      </div>

    </div>
    <hr class="my-hr">
    <div id="articleComment" style="margin-bottom: 3rem;">
        <span v-for="item in comments">
          <table class="comment-table" cellpadding="0" cellspacing="0">
            <tr>
              <td rowspan="4" style="width: 15%;" align="center" valign="top">
                <img src="../../assets/images/header.jpeg" style="width: 80%;border-radius: 50%;margin-top: 20%"/>
              </td>
            </tr>
            <tr style="font-size: 0.8rem">
              <td style="color: #26a2ff;">游客_1573</td>
              <td align="right">
                <i class="icon-grin" @click="addZan($event)"></i>
                <span>123</span>
              </td>
            </tr>
            <tr>
              <td colspan="2" style="font-size: 0.9rem">
                <span v-html="item.content"></span>
              </td>
            </tr>
            <tr>
              <td colspan="2" style="font-size: 0.7rem;">
                <span v-html="item.time"></span> ·
                <div class="comment-res">
                  23回复
                </div>
              </td>
            </tr>
          </table>
        </span>
    </div>
    <div class="footer">
      <table cellspacing="0" cellpadding="0" style="width: 100%">
        <tr>
          <td v-show="!discussing" @click="goBackList" style="padding: 0 1rem 0.3rem 0.7rem;">
            <img src="../../assets/images/left.png" align="middle" style="width: 1.3rem"/>
          </td>
          <td>
            <input id="discussing" :class="{'my-input': true, 'my-input-discuss': discussing}"
                   :placeholder="placeholder"
                   v-model="discuss" v-on:blur="inputBlur" @focus="inputClick"/>
          </td>
          <td v-if="!discussing" @click="goToComments" style="width: 15%;text-align: center">
            <img src="../../assets/images/ali_message2.png" style="width: 1.8rem;" align="middle"/>
          </td>
          <td v-if="!discussing" @click="getCollection" style="width: 15%;text-align: center">
            <img v-show="!collection" src="../../assets/images/ali_coll_emp.png" align="middle"
                 style="width: 1.7rem;"/>
            <img v-show="collection" src="../../assets/images/ali_coll_fill.png" align="middle"
                 style="width: 1.7rem;"/>
          </td>
          <td v-if="!discussing" @click="report" style="width: 15%;text-align: center">
            <img src="../../assets/images/alarm63.png" align="middle" style="width: 1.6rem;"/>
          </td>
          <td v-if="discussing" @click="pubComment" align="middle" style="width: 20%">
            <button
              :class="{'my-button': 'my-button', 'my-button-ok': myButtonOk, 'my-button-bao': this.discuss.trim() !== '' && isJuBao}">
              {{buttonValue}}
            </button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
  export default {
    name: "article-detail",
    data() {
      return {
        scroll: 0,
        buttonValue: '',
        placeholder: '发表神评论...',
        isJuBao: false,
        discuss: '',
        discussing: false,
        myButtonOk: false,
        hasClick: false,
        collection: false,
        zanContent: false,
        caiContent: false,
        zanCommentPicUrl: require('../../assets/images/icon_like.png'), //动态切换图片
        comments: [
          {content: "JavaScript默认的时间格式我们一般情况下不会用", time: new Date().toLocaleString()},
          {content: "我发现评论都有好多版本啊。我亲身经历过，讲下我经历的版本。", time: new Date().toLocaleString()},
          {content: "一个飞虎队，一个猎狐者，还有个母体僵尸。然后这三个排队绕着固定路线跑来跑去，头上都是没名字的。打也打不到，抓也抓不到。", time: new Date().toLocaleString()},
        ]
      };
    },
    methods: {
      goBackList() {
        window.history.go(-1);
      },
      inputBlur() {
        this.discussing = false;
        if (this.discuss.trim() === '' && this.isJuBao) {
          this.placeholder = '发表神评论...';
          this.isJuBao = false;
        }
      },
      inputClick() {
        this.discussing = true;
        if (this.isJuBao) {
          this.buttonValue = '举报';
          this.placeholder = '请填写举报理由...';
        } else {
          this.buttonValue = '发布';
          this.placeholder = '发表神评论...';
        }
      },
      goToComments() {
        if (this.hasClick) {
          window.scrollTo(0, this.scroll);
        } else {
          window.scrollTo(0, (document.getElementById("articleDetail").offsetHeight - document.getElementById("articleComment").offsetHeight));
        }
        this.hasClick = !this.hasClick;
      },
      getCollection() {
        this.collection = !this.collection;
      },
      getZanContent(e) {
        if (this.zanContent) {
          e.target.innerHTML = Number(e.target.innerHTML) - 1;
        } else {
          e.target.innerHTML = Number(e.target.innerHTML) + 1;
        }
        this.zanContent = !this.zanContent;
      },
      getCaiContent(e) {
        if(this.caiContent) {
          e.target.innerHTML = Number(e.target.innerHTML) - 1;
        } else {
          e.target.innerHTML = Number(e.target.innerHTML) + 1;
        }
        this.caiContent = !this.caiContent;
      },
      pubComment() {
        if (this.discuss.trim() === '') {
          this.isJuBao = false;
          this.placeholder = '发表神评论...';
          return;
        }
        if (this.isJuBao) {
          console.info("举报成功: " + this.discuss);
          this.isJuBao = false;
          this.placeholder = '发表神评论...';
        } else {
          this.comments.unshift({content: this.discuss, time: new Date().toLocaleString()});
          window.scrollTo(0, (document.getElementById("articleDetail").offsetHeight - document.getElementById("articleComment").offsetHeight) - 10);
        }
        this.discuss = '';
      },
      addZan(e) {
        //e.target.src = require('../../assets/images/icon_like_alt.png');
        let classList = e.target.classList;
        let innerHTML = e.target.nextElementSibling.innerHTML;
        if (classList.contains('icon-grin2')) {
          classList.remove('icon-grin2');
          classList.add('icon-grin');
          innerHTML = Number(innerHTML) - 1;
        } else if (classList.contains('icon-grin')) {
          classList.remove('icon-grin');
          classList.add('icon-grin2');
          innerHTML = Number(innerHTML) + 1;
        }
        e.target.nextElementSibling.innerHTML = innerHTML;
      },
      report() {
        this.isJuBao = true;
        document.getElementById('discussing').focus();
      }
    },
    mounted() {
      let _this = this;
      document.getElementById('articleDetail').addEventListener('touchend', function (e) {
        let className = e.target.parentNode.parentNode.parentNode.parentNode.className;
        if (className !== 'footer') {
          _this.scroll = window.scrollY;
        }
      })
    },
    watch: {
      discuss() {
        if (this.discuss.trim() === '') {
          this.myButtonOk = false;
        } else {
          this.myButtonOk = true;
        }
      }
    }
  }
</script>

<style scoped>
  .focus-button {
    float: right;
    border: 0;
    border-radius: 0.2rem;
    height: 2.0rem;
    background-color: #26a2ff;
    margin: 0.5rem;
    padding: 0 1.2rem;
    color: white;
  }

  .comment-table {
    width: 100%;
    border-collapse: separate;
    border-spacing: 0px 0.4rem;
  }

  .footer {
    height: 2.0rem;
    position: fixed;
    bottom: 0;
    width: 96%;
    display: table;
    background-color: #fdfdfd;
    padding: 0.3rem 0;
    border: 1px solid #ebebeb;
    border-bottom: none;
    border-left: none;
    border-right: none;

  }

  div.footer span {
    vertical-align: middle;
    display: table-cell;
  }

  .my-input {
    border-radius: 0.2rem;
    height: 1.7rem;
    font-size: 0.9rem;
    border: 0.12rem solid #a9acb1;
    padding-left: 0.5rem;
    width: 80%;
  }

  .my-input-discuss {
    width: 97%;
  }

  .my-button {
    height: 1.9rem;
    border-radius: 0.25rem;
    border: 0px solid #a9acb1;
    margin-left: 0.3rem;
    padding: 0 1rem;
  }

  .my-button-ok {
    background-color: #26a2ff;
    color: aliceblue;
  }

  .comment-res {
    display: inline-block;
    background-color: #f0f0f0;
    border-radius: 1rem;
    padding: 0.25rem 0.35rem;
  }

  .zan-cai-num {
    border: 1px solid #a9acb1;
    height: 16rem;
    padding: 0.4rem 2rem;
    border-radius: 2rem;
    vertical-align: bottom;
  }

  .my-button-bao {
    background-color: red;
  }
</style>
