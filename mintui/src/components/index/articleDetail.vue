<template>
  <div style="position: absolute;">
    <div>
      <div style="font-weight: bold;font-size: 1.25rem">
        中国还有哪鲜为人知可以超越腾讯、阿里的公司仍未上市？
      </div>
      <div style="text-align: right;margin: 0.5rem">
        上观 2018-03-07 06:30:47
      </div>
    </div>
    <hr class="my-hr">
    <div>
      在全国政协十三届一次会议上，施一公在参与讨论《政府工作报告》时发现，过去5年里，国内有效发明专利拥有量增加了两倍，技术交易额翻了一番。“如果较真一点，假设国内有效发明专利从100增加到300，技术交易额从100变成了200，那么单个专利交易额其实是不增反降了，是5年前的66%。”施一公算了一笔账。<br/>
      这一数据折射出来的，是科技评价体系的问题。施一公称，在各个单位，不论是晋升还是考量绩效，都会把专利、发表文章、文章的引用数和文章所发表杂志的影响因子作为标准，而且这一风气愈演愈烈。<br/>
      “但这几个核心的科技评价指标——文章数量、论文引用率、杂志的影响因子——都可以人为地提高。我想大家知道我这句话的意思。”<br/>
      他表示，各个学校、单位都办了杂志，想要提高影响因子，互相引用就可以，引用多了，‘影响因子’自然也就高了。“中国这样的国家，想要把这3个指标做上去，我认为是易如反掌。”在他看来，论文不足以说明科技实力，美国没有这样的评价方式，科技实力却依然领先。“因此，论文和科技实力是两回事，大家千万要分开。”<br/>
      不过他也承认，数字指标在各个单位都很重要，影响到评奖、评优、评先：“这是矛盾的两方面，一方面如果没有这些标准，如何反映科研的质量，但有了这些标准，大家拼命‘做’这个数字怎么办？”<br/>
      “有些文章，通俗点叫‘垃圾文章’，就是纯粹为了发文而发文，这种情况太多了。”谈到业内和社会各界出现的“论文无用”论，施一公表示“坚决反对”。“没有论文，怎么能有科学技术？但在追求论文的导向如此之强的时候，评价立场如此鲜明的时候，造成的负面影响其实非常大。”<br/>
      如何改变现状？“说实话，我也没有特别好的办法。如果要提建议，就是应该进一步改进科学评价体系，这需要全社会达成一种共识。”施一公介绍，国际通用的方法是参考国际最顶尖专家的观点：“这是一种‘唯专家论’的方式，我不评价这种做法的利弊，但至少在很多国家都是这样的。如果是将本领域所有专家聚在一起，通过抽签确定评价委员会，在科学上，这样的民主是不能带领科学健康向前发展的。”
    </div>
    <hr class="my-hr">
    <div>
      广告
    </div>
    <hr class="my-hr">
    <div>
      推荐列表
    </div>
    <hr class="my-hr">
    <div>
      评论
    </div>
    <div class="footer">
      <span style="padding: 0 0.4rem;font-weight: bold;" @click="goBackList"><</span>
      <span style="padding-right: 0.2rem"><input style="border-radius: 0.2rem;"/></span>
      <div style="display: inline-block;">
        <span>300</span>
        <span>收藏</span>
        <span>分享</span>
      </div>

    </div>
  </div>
</template>

<script>
  export default {
    name: "article-detail",
    methods: {
      goBackList() {
        window.history.go(-1);
      }
    }
  }
</script>

<style scoped>
  .footer {
    height: 1.62rem;
    position: fixed;
    bottom: 0;
    background-color: red;
    width: 96%;
  }
  div.footer span {
    margin-left: 0.2rem;
  }
</style>
