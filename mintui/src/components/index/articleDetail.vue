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
              <button class="focus-button">关注</button>
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
        “有些文章，通俗点叫‘垃圾文章’，就是纯粹为了发文而发文，这种情况太多了。”谈到业内和社会各界出现的“论文无用”论，施一公表示“坚决反对”。“没有论文，怎么能有科学技术？但在追求论文的导向如此之强的时候，评价立场如此鲜明的时候，造成的负面影响其实非常大。”<br/><br/>
        如何改变现状？“说实话，我也没有特别好的办法。如果要提建议，就是应该进一步改进科学评价体系，这需要全社会达成一种共识。”施一公介绍，国际通用的方法是参考国际最顶尖专家的观点：“这是一种‘唯专家论’的方式，我不评价这种做法的利弊，但至少在很多国家都是这样的。如果是将本领域所有专家聚在一起，通过抽签确定评价委员会，在科学上，这样的民主是不能带领科学健康向前发展的。”
      </div>
      <div style="text-align: center;margin-top: 1.5rem;margin-bottom: 1rem">
        <span @click="getZanContent" class="zan-cai-num">
          <img v-if="!zanContent" src="../../assets/images/thumb22.png" align="middle" style="width: 2rem"/>
          <img v-if="zanContent" src="../../assets/images/thumb20.png" align="middle" style="width: 2rem"/>
          <span style="vertical-align: sub;margin-left: -0.6rem">888</span>
        </span>
        <span @click="getCaiContent" class="zan-cai-num" style="margin-left: 10%">
          <img v-if="!caiContent" src="../../assets/images/thumb21.png" align="middle" style="width: 2rem;"/>
          <img v-if="caiContent" src="../../assets/images/thumb19.png" align="middle" style="width: 2rem;"/>
          <span style="vertical-align: sub;margin-left: -0.6rem">999</span>
        </span>
      </div>

    </div>
    <hr class="my-hr">
    <div id="articleComment" style="margin-bottom: 3rem;">
      <div>
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
                <img :src="zanCommentPicUrl" style="width: 0.9rem;vertical-align: middle" @click="addZan($event)"/>
                <span style="vertical-align: middle;">123</span>
              </td>
            </tr>
            <tr>
              <td colspan="2" style="font-size: 0.9rem">
                <span v-html="item.content"></span>
              </td>
            </tr>
            <tr>
              <td colspan="2" style="font-size: 0.75rem;">
                <span v-html="item.time"></span> ·
                <div class="comment-res">
                  23回复
                </div>
              </td>
            </tr>
          </table>
        </span>
      </div>
    </div>
    <div class="footer">
      <table cellspacing="0" cellpadding="0" style="width: 100%">
        <tr>
          <td v-show="!discussing" @click="goBackList" style="padding: 0 1rem 0.3rem 0.7rem;">
            <img src="../../assets/images/arrow270.png" align="middle" style="width: 1.3rem"/>
          </td>
          <td>
            <input id="discussing" :class="{'my-input': true, 'my-input-discuss': discussing}"
                   :placeholder="placeholder"
                   v-model="discuss" v-on:blur="inputBlur" @focus="inputClick"/>
          </td>
          <td v-if="!discussing" @click="goToComments">
            <img src="../../assets/images/message5.png" style="width: 1.8rem;float: left" align="middle"/>
            <span class="commentNum">99+</span>
          </td>
          <td v-if="!discussing" @click="getCollection">
            <img v-show="!collection" src="../../assets/images/star_empty.png" align="middle"
                 style="width: 1.8rem;margin: 0 0.5rem;"/>
            <img v-show="collection" src="../../assets/images/star_fill.png" align="middle"
                 style="width: 1.8rem;margin: 0 0.5rem;"/>
          </td>
          <td v-if="!discussing" @click="report">
            <img src="../../assets/images/light53.png" align="middle" style="width: 1.65rem;margin: 0 0.5rem;"/>
          </td>
          <td v-if="discussing" @click="pubComment" align="middle">
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
        'my-input': 'my-input',
        'my-input-discuss': 'my-input-discuss',
        'my-button': 'my-button',
        'my-button-ok': 'my-button-ok',
        'my-button-bao': 'my-button-bao',
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
          window.scrollTo(0, 0);
        } else {
          window.scrollTo(0, (document.getElementById("articleDetail").offsetHeight - document.getElementById("articleComment").offsetHeight));
        }
        this.hasClick = !this.hasClick;
      },
      getCollection() {
        this.collection = !this.collection;
      },
      getZanContent() {
        this.zanContent = !this.zanContent;
      },
      getCaiContent() {
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
        e.target.src = require('../../assets/images/icon_like_alt.png');
        e.target.nextElementSibling.innerHTML = "456"
      },
      report() {
        this.isJuBao = true;
        document.getElementById('discussing').focus();
      }
    },
    mounted() {
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
    font-size: 0.95rem;
    border-collapse: separate;
    border-spacing: 0px 0.4rem;
    color: black;
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
    border: 1px solid #a9acb1;
    padding-left: 0.5rem;
    width: 80%;
  }

  .my-input-discuss {
    width: 100%;
  }

  .my-button {
    height: 1.9rem;
    border-radius: 0.25rem;
    border: 0px solid #a9acb1;
    margin-left: 0.5rem;
    padding: 0 1rem;
  }

  .my-button-ok {
    background-color: #26a2ff;
    color: aliceblue;
  }

  .commentNum {
    float: right;
    margin-left: -1.25rem;
    background-color: #26a2ff;
    color: #fafafa;
    border-radius: 0.4rem;
    padding: 0 0.25rem;
    font-size: 0.7rem;
  }

  .comment-res {
    display: inline-block;
    background-color: #f0f0f0;
    border-radius: 1rem;
    padding: 0.25rem 0.35rem;
  }

  .zan-cai-num {
    border: 1px solid #ebebeb;
    height: 16rem;
    padding: 0.2rem 1.5rem 0.8rem;
    border-radius: 2rem;
  }

  .my-button-bao {
    background-color: red;
  }
</style>
