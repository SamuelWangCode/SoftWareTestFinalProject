<template>

<div class="page-has-left-panels">
<!-- Fixed Sidebar Left -->

<div id="mypreloader">
<loading></loading>
</div>

<headline></headline>


<div class="header-spacer header-spacer-small"></div>


<div class="container">
	<div class="row mt50">
		<div class="col col-xl-8 m-auto col-lg-12 col-md-12 col-sm-12 col-12">
			<div class="ui-block">

				
				<!-- Single Post -->
				
				<article class="hentry blog-post single-post single-post-v2">
				
					<img :src=(temp+guide.championImg) alt="author">
					<h2 class="h1 post-title">{{guide.title}}</h2>
				
					<div class="post-comments" align="right">
						<a class="h6 comments">{{guide.likeNum}}</a>
						<span>Likes</span>
						<a @click="likeguide"><i class="fa fa-thumbs-up" aria-hidden="true"></i></a>
					</div>
				
				
					<div class="post-content-wrap">
				
						<div class="post-content">
				
							<h3>Early Game Items:</h3>
				
							<img :src=(itemp+guide.item1+back) alt="author">
							<img :src=(itemp+guide.item2+back) alt="author">
							<br>
							<h3>Middle Game Items:</h3>
				
							<img :src=(itemp+guide.item3+back) alt="author">
							<img :src=(itemp+guide.item4+back) alt="author">

							<br>
							<h3>Later Game Items:</h3>
				

							<img :src=(itemp+guide.item5+back) alt="author">
							<img :src=(itemp+guide.item6+back) alt="author">
							<br>
							<h3>Introduction:</h3>
				
							<p>{{guide.context}}</p>
						</div>
					</div>
				

				
				</article>
				
				<!-- ... end Single Post -->

				
			</div>
		</div>
	</div>
</div>



<a class="back-to-top" href="#">
	<i class="fa fa-arrow-up fa-2x" aria-hidden="true"></i>
</a>


</div>


</template>>

<script>
import headline from '@/components/subgroup/Head.vue'
import loading from '@/components/subgroup/Loading.vue'

export default {
  name: 'guideInfo',
  data () {
    return {
        guide: [],
        gid: this.$route.query.Gid,
		temp: '/static/img/champion/',
		itemp: '/static/img/item/',
		back: '.png',
    }
  },
  methods: {
      getGuideInfo(){
		this.$axios
        .get('/guide-producer/api/searchGuideBygID/' + this.gid)
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.guide = successResponse.data.data
            this.$dlg.toast("success", {messageType: 'success', closeTime: 5})
          }
          if (successResponse.data.code === 400) {
            this.$dlg.toast(successResponse.data.msg, {messageType: 'error', closeTime: 5})
          }
        })
        .catch(failResponse => {})
      },
      picFadeout(){
		setTimeout(function () {
			$('#mypreloader').fadeOut(1000);
	    }, 1500)
	  },
	  likeguide(){
		this.$axios
        .get('/guide-producer/api/addLikeNum/' + this.gid)
        .then(successResponse => {
          if (successResponse.data.code === 200) {
			this.guide.likeNum++
            this.$dlg.toast("success", {messageType: 'success', closeTime: 5})
          }
          if (successResponse.data.code === 400) {
            this.$dlg.toast(successResponse.data.msg, {messageType: 'error', closeTime: 5})
          }
        })
        .catch(failResponse => {})		  
	  }
      
  },
  mounted() {
      this.getGuideInfo()
      this.picFadeout()
  },
  components:{
	  headline,
	  loading
  },
}
</script>