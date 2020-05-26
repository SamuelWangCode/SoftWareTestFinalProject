<template>

<div class="page-has-left-panels">
<!-- Fixed Sidebar Left -->

<div id="mypreloader">
<loading></loading>
</div>


<headline></headline>


<div class="header-spacer header-spacer-small"></div>


<div class="main-header">
	<div ></div>
	<div class="content-bg-wrap bg-badges">
		<div class="row">
			<div class="col col-lg-8 m-auto col-md-8 col-sm-12 col-12">
				<div class="main-header-content">
					<h1>Your Account Dashboard</h1>
					<p>Welcome to your account dashboard! Here you’ll find everything you need to change your profile!!</p>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- ... end Main Header Account -->


<!-- Your Account Personal Information -->

<div class="container">
	<div class="row">
		<div class="col col-xl-9 order-xl-2 col-lg-9 order-lg-2 col-md-12 order-md-1 col-sm-12 col-12">
			<div class="ui-block">
				<div class="ui-block-title">
					<h6 class="title">My Guide</h6>
					<a href="#" class="more"><svg class="olymp-three-dots-icon"><use xlink:href="svg-icons/sprites/icons.svg#olymp-three-dots-icon"></use></svg></a>
				</div>

				
				<!-- Notification List Frien Requests -->
				
				<ul class="notification-list friend-requests">
					<li v-for="(guide, index) in guides" :key="index">
						<div class="author-thumb">
							<img :src=(temp+guide.championImg) alt="author">
						</div>
						<div class="notification-event">
							<a @click="goTo(guide.gid)" class="h6 notification-friend">{{guide.title}}</a>
                            <span class="chat-message-item">{{guide.context}}</span>
						</div>
						<!-- <span class="notification-icon">
	                        <a data-toggle="modal" data-target="#create-photo-album" class="btn btn-primary btn-md-2" style="float:right">删除</a>            
						</span>                         -->
					</li>
					<div v-show="!guides.length" class="text" style="text-align:center">
                         He hasn't post Guide
                    </div>			
				</ul>
				
				<!-- ... end Notification List Frien Requests -->
			</div>

		</div>

		<div class="col col-xl-3 order-xl-1 col-lg-3 order-lg-1 col-md-12 order-md-2 col-sm-12 col-12 responsive-display-none">
			<div class="ui-block">

				<!-- Your Profile  -->
				
				<div class="your-profile">
					<div class="ui-block-title ui-block-title-small">
						<h6 class="title">HIS INFORMATION</h6>

						<a @click="subscribeHim" data-toggle="modal" data-target="#create-photo-album" class="btn btn-primary btn-md-2" style="float:right">关注</a>

					</div>
				
					<div id="accordion" role="tablist" aria-multiselectable="true">
						<div class="card">
							<div class="card-header" role="tab" id="headingOne">
								<h6 class="mb-0">
									<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
										Profile Settings
										<svg class="olymp-dropdown-arrow-icon"><use xlink:href="svg-icons/sprites/icons.svg#olymp-dropdown-arrow-icon"></use></svg>
									</a>					
								</h6>
								
							</div>
				
							<div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
								<ul class="your-profile-menu">
									<li>
										<a @click="moveToOpage(ooid)">His Personal Information</a>
									</li>
									<li>
										<a @click="moveToOguide(ooid)">His Guides</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				
				<!-- ... end Your Profile  -->

			</div>
		</div>
	</div>
</div>

<!-- ... end Your Account Personal Information -->



<a class="back-to-top" href="#">
	<i class="fa fa-arrow-up fa-2x" aria-hidden="true"></i>
</a>


</div>


</template>>

<script>
import headline from '@/components/subgroup/Head.vue'
import loading from '@/components/subgroup/Loading.vue'
setTimeout(function () {
			$('#mypreloader').fadeOut(600);
		}, 1500)
export default {
  name: 'personalGuide',
  data () {
    return {
		guides: [],
        temp: '/static/img/champion/',
        ooid: sessionStorage.getItem("OoID"),
		uid : sessionStorage.getItem("UuID")	
    }
  },
  methods: {
	  init(){
		this.$axios
		.get('/guide-producer/api/searchGuideByUid/' + this.ooid)
        .then(successResponse => {
			this.responseResult = JSON.stringify(successResponse.data)
        	if (successResponse.data.code === 200) {
                this.guides = successResponse.data.data				
        	}
        	if (successResponse.data.code === 400) {
				alert(successResponse.data.message)
			}
        })
        .catch(failResponse => {})
      },
      subscribeHim(){
        this.$axios
        .get('/manageInformation-producer/api/subscribe/' + this.uid + '/' + this.ooid)
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            alert(successResponse.data.message)       
          }
          if (successResponse.data.code === 400) {
            alert(successResponse.data.message)
          }
        })
        .catch(failResponse => {})
	  },
	  moveToOpage(oid){
        this.$router.push({name:'OPersonalInfo',query:{Oid:oid}});
	  },
	  moveToOguide(oid){
        this.$router.push({name:'OGuide',query:{Oid:oid}});
	  },
	  picFadeout(){
		setTimeout(function () {
			$('#mypreloader').fadeOut(600);
		}, 1500)
	  },
      goTo(gid){
            this.$router.push({path:'/guideinfo',query:{Gid:gid}});
      },		  
            
  },
  mounted() {
	this.init()
	this.picFadeout()
  },
  created(){
  },
  components:{
	  headline,
	  loading
  },
}
</script>