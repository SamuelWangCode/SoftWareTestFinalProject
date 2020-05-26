<template>

<div class="page-has-left-panels">
<!-- Fixed Sidebar Left -->

<div id="mypreloader">
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
						<img src='/static/img/landing-users.jpg'  width="1280px" height="400px">
						
						<div class="top-header-author">
							<div class="author-thumb">
								<img :src=(temp+champion.fullImage) width="100%" height="100%">
							</div>
							<div class="author-content">
								<a href="#" class="h3 author-name">{{champion.alias}}</a>
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
					    <h5 class="title">Description</h5>
				    </div>				
				    <table class="event-item-table">
					    <tbody>
						    <div class="header-spacer1"></div>
						    <tr class="event-item">
							    <div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								    <div class="ui-block">	
									    <div class="birthday-item inline-items badges">
										    <div class="birthday-author-name">
											    <div class="birthday-date">{{champion.lore}}</div>
										    </div>							
									    </div>
								    </div>
							    </div>
						    </tr>
					    </tbody>
				    </table>
			</div>                
            <div class="ui-block">
				    <div class="ui-block-title ui-block-title-small">
					    <h5 class="title">Passive</h5>
				    </div>				
				    <table class="event-item-table">
					    <tbody>
						    <div class="header-spacer1"></div>
						    <tr class="event-item">
							    <div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								    <div class="ui-block">	
									    <div class="birthday-item inline-items badges">
										    <div class="author-thumb">
											    <img :src="'/static/img/passive/'+champion.passiveImg"  alt="author">
										    </div>
										    <div class="birthday-author-name">
											    <a href="#" class="h6 author-name">{{champion.passiveName}}</a>
											    <div class="birthday-date">{{champion.passiveDes}}</div>
										    </div>							
									    </div>
								    </div>
							    </div>
						    </tr>
					    </tbody>
				    </table>
			</div>
			<div class="ui-block">
				<div class="ui-block-title ui-block-title-small">
					<h5 class="title">Skills</h5>
				</div>				
				<table class="event-item-table">
					<tbody>
						<div class="header-spacer1"></div>
						<tr class="event-item">
							<div class="col col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
								<div class="ui-block">	
									<div class="birthday-item inline-items badges">
										<div class="author-thumb">
											<img :src="'/static/img/spell/'+champion.skillOneImg"  alt="author">
										</div>
										<div class="birthday-author-name">
											<a href="#" class="h6 author-name">{{champion.skillOneName}}</a>
											<div class="birthday-date">{{champion.skillOneDes}}</div>
										</div>							
									</div>
								</div>
                                <div class="ui-block">	
									<div class="birthday-item inline-items badges">
										<div class="author-thumb">
											<img :src="'/static/img/spell/'+champion.skillTwoImg"  alt="author">
										</div>
										<div class="birthday-author-name">
											<a href="#" class="h6 author-name">{{champion.skillTwoName}}</a>
											<div class="birthday-date">{{champion.skillTwoDes}}</div>
										</div>							
									</div>
								</div>
                                <div class="ui-block">	
									<div class="birthday-item inline-items badges">
										<div class="author-thumb">
											<img :src="'/static/img/spell/'+champion.skillThrImg"  alt="author">
										</div>
										<div class="birthday-author-name">
											<a href="#" class="h6 author-name">{{champion.skillThrName}}</a>
											<div class="birthday-date">{{champion.skillThrDes}}</div>
										</div>							
									</div>
								</div>
                                <div class="ui-block">	
									<div class="birthday-item inline-items badges">
										<div class="author-thumb">
											<img :src="'/static/img/spell/'+champion.skillFouImg"  alt="author">
										</div>
										<div class="birthday-author-name">
											<a href="#" class="h6 author-name">{{champion.skillFouName}}</a>
											<div class="birthday-date">{{champion.skillFouDes}}</div>
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
  name: 'championInfo',
  data () {
    return {
        champion: [],
        cid: this.$route.query.Cid,
        temp: '/static/img/champion/',
    }
  },
  methods: {
      getChampInfo(){
		this.$axios
        .get('/searchChampion-producer/api/checkChamp/' + this.cid)
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.champion = successResponse.data.data
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
      
  },
  mounted() {
      this.getChampInfo()
      this.picFadeout()
  },
  components:{
	  headline,
	  loading
  },
}
</script>

