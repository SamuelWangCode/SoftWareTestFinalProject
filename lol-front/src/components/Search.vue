<template>

<div class="page-has-left-panels">
<!-- Fixed Sidebar Left -->

<div v-show="ISflag">
<loading></loading>
</div>

<headline></headline>


<div class="header-spacer header-spacer-small"></div>


<!-- Main Header Badges -->


<div class="container">
	<div class="row">
		<div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			<div class="ui-block">
				<div class="top-header top-header-favorit">
					<div class="top-header-thumb">
						<img :src="summonerBest"  width="1280px" height="400px">
						
						<div class="top-header-author">
							<div class="author-thumb">
								<img :src="summonerIcon" width="100%" height="100%">
							</div>
							<div class="author-content">
								<a href="#" class="h3 author-name">{{summonerName}}</a>
							</div>
						</div>
					</div>
					<div class="profile-section">
						<div class="row">
							<div class="col col-xl-12 m-auto col-lg-8 col-md-10">
								<ul class="profile-menu">
									<li>
										<a>Level: {{summonerLevel}}</a>
									</li>
									<li>
										<a>Rank: {{summonerRank}}</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- ... end Main Header Badges -->


<!-- Main Content Badges -->


<div class="container">
	<div class="row">
		<div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
			<div class="ui-block">
				<div class="ui-block-title ui-block-title-small">
					<h5 class="title">Recent Match</h5>
				</div>				
				<table class="event-item-table">
					<tbody>
						<div class="header-spacer1"></div>
						<tr class="event-item">
							<div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<div v-for="(item,ii) in summonerMatchInfo" :key="ii" >
								<div class="ui-block">	
									<div class="birthday-item inline-items badges">
										<div class="author-thumb">
											<img :src="'/static/img/champion/'+item.matchChampion+'.png'"  alt="author">
										</div>
										<div class="birthday-author-name">
											<a href="#" class="h6 author-name">{{item.matchChampion}}</a>
											<div class="birthday-date">Kill: {{item.matchKill}} / Assists: {{item.matchAssist}}/ Death: {{item.matchDeath}}</div>
										</div>
										<div class="skills-item">
												 {{item.matchVD}}
										</div>								
									</div>
								</div>
								</div>
							</div>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>


<!-- ... end Main Content Badges -->



<a class="back-to-top" href="#">
	<i class="fa fa-arrow-up fa-2x" aria-hidden="true"></i>
</a>


</div>


</template>>

<script>
import headline from '@/components/subgroup/Head.vue'
import loading from '@/components/subgroup/Loading.vue'
export default {
  name: 'videPage',
  data () {
    return {
	  summonerLevel: '',
	  summonerRank: '',
	  summonerIcon: '',
	  summonerBest: '',
	  summonerMatchInfo: '',
	  summonerName:'',
	  ISflag: sessionStorage.getItem("Isflag")
    }
  },
  methods: {
    getInfo () {
      this.$axios
        .get('/searchSummoner-producer/api/search/' + sessionStorage.getItem("SummonerName"))
        .then(successResponse => {
          this.responseResult = JSON.stringify(successResponse.data)
          if (successResponse.data.code === 200) {
			this.summonerLevel = successResponse.data.data.summonerLevel
			this.summonerIcon = ('/static/img/profileicon/'+successResponse.data.data.summonerIcon+".png")
			this.summonerRank =  successResponse.data.data.summonerRank
			this.summonerName =  sessionStorage.getItem("SummonerName")
			this.summonerBest =  ('/static/img/splash/'+successResponse.data.data.summonerBestC+"_0.jpg")
			this.summonerMatchInfo =  successResponse.data.data.summonerMatch
			sessionStorage.setItem("Isflag",false)
			
			this.ISflag = !this.ISflag
          }
          if (successResponse.data.code === 400) {
			alert(successResponse.data.message)
			this.$router.push({name:'Homepage'});
          }
        })
        .catch(failResponse => {})
	},
  },
  mounted() {
      this.getInfo()
  },
  components:{
	  headline,
	  loading
  },
}
</script>

