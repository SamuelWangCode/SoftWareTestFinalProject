<template>
<div>
	<div class="fixed-sidebar">
	<div class="fixed-sidebar-left sidebar--small" id="sidebar-left">

		<a href="/home" class="logo">
			<div class="img-wrap">
				<img src="@/assets/img/logo.png" alt="Olympus">
			</div>
		</a>

		<div class="mCustomScrollbar" data-mcs-theme="dark">
			<ul class="left-menu">
				<li>
					<a href="#" class="js-sidebar-open">
						<i class="fa fa-bars fa-2x" aria-hidden="true"></i>
					</a>
				</li>
				<li>
					<a href="http://localhost:8080/searchchamp">
						<i class="fa fa-address-book fa-2x" aria-hidden="true"></i>
					</a>
				</li>
				<li>
					<a href="http://localhost:8080/searchguide">
						<i class="fa fa-search-plus fa-2x" aria-hidden="true"></i>
					</a>
				</li>
			
			</ul>
		</div>
	</div>

	<div class="fixed-sidebar-left sidebar--large" id="sidebar-left-1">
		<a href="/home" class="logo">
			<div class="img-wrap">
				<img src="@/assets/img/logo.png" alt="Olympus">
			</div>
			<div class="title-block">
				<h6 class="logo-title">LolSearcher</h6>
			</div>
		</a>

		<div class="mCustomScrollbar" data-mcs-theme="dark">
			<ul class="left-menu">
				<li>
					<a href="#" class="js-sidebar-open">
						<i class="fa fa-bars fa-2x" aria-hidden="true"></i>
						<span class="left-menu-title" style="marginLeft:10px">Menu</span>
					</a>
				</li>
				<li>
					<a href="http://localhost:8080/searchchamp">
						<i class="fa fa-address-book fa-2x" aria-hidden="true"></i>
						<span class="left-menu-title" style="marginLeft:10px">Champion Information</span>
					</a>
				</li>
				<li>
					<a href="http://localhost:8080/searchguide">
						<i class="fa fa-search-plus fa-2x" aria-hidden="true"></i>
						
						<span class="left-menu-title" style="marginLeft:10px">Walkthrough</span>
					</a>
				</li>
				
				
			</ul>
		</div>
	</div>
</div>

<!-- ... end Fixed Sidebar Left -->

<!-- Header-BP -->

<header class="header" id="site-header">

	<div class="page-title">
		<h6>Home Page</h6>
	</div>

	<div class="header-content-wrapper">
		<form class="search-bar">
			<div class="form-group with-button">
				<input class="form-control" placeholder="Search Summoner Name here..." type="text" v-model="summonerInfo.summonerName" required>			
    				<button @click="search">
						<i class="fa fa-search fa-lg" aria-hidden="true"></i>
				  	</button>				
			</div>
		</form>
		<div class="control-block">

			<div class="control-icon more has-items">
				<a href="http://localhost:8080/myfollows">
					<i class="fa fa-child fa-2x" aria-hidden="true"></i>
				</a>
			</div>


			<div class="author-page author vcard inline-items more">
				<div class="author-thumb" v-show="islog">
					<img alt="author" :src=UserAccount.icon width='30' height="30" class="avatar">
					<span class="icon-status online"></span>
					<div class="more-dropdown more-with-triangle">
						<div class="mCustomScrollbar" data-mcs-theme="dark">
							<div class="ui-block-title ui-block-title-small">
								<h6 class="title">Your Account</h6>
							</div>

							<ul class="account-settings">
								<li>
									<a href="http://localhost:8080/personalinfo">

										<i class="fa fa-cog" aria-hidden="true"></i>

										<span>Account Settings</span>
									</a>
								</li>
								<li>
									<a href="http://localhost:8080/postguide">
										<i class="fa fa-bars" aria-hidden="true"></i>
										<span>Create Guide</span>
									</a>
								</li>
								<li>
									<a href="#" @click="logout">
										<i class="fa fa-sign-out" aria-hidden="true"></i>

										<span>Log Out</span>

									</a>
								</li>
							</ul>
						</div>

					</div>
				</div>
				<a>
					<div class="author-title" v-if="islog">
						{{UserAccount.name}}<svg class="olymp-dropdown-arrow-icon"><use xlink:href="svg-icons/sprites/icons.svg#olymp-dropdown-arrow-icon"></use></svg>
					</div>
					<div v-else class="author-title" v-show="!islog">
						<a href="/Landingpage">Login Now</a>
					</div>

				</a>
			</div>

		</div>
	</div>
</header>
</div>
</template>>
<script>
export default {
  name: 'headline',
  data () {
    return {
	  summonerInfo: {summonerName: ''},
	  islog: sessionStorage.getItem("IsLog"),
	  UserAccount:{
		account: sessionStorage.getItem("UuAccount") , 
		name:sessionStorage.getItem("UuName") , 
	  	icon:'/static/img/userIcon/default.jpg', 
	    uid : sessionStorage.getItem("UuID")}
    }
  },
  methods: {
    search () {
		sessionStorage.setItem("SummonerName",this.summonerInfo.summonerName)
		sessionStorage.setItem("Isflag",true)
		this.$router.push({name:'Search'})
	},
	logout(){

		sessionStorage.removeItem("UuAccount")
		sessionStorage.removeItem("UuID")
		sessionStorage.removeItem("UuName")
		sessionStorage.removeItem("UuImage")
		sessionStorage.removeItem("UuGender")
		sessionStorage.removeItem("UuPrefer")
		sessionStorage.setItem("Islog" , false)
		alert("LogOut Successfully")
        location.reload()
	},
	setData(){
		this.UserAccount.account=sessionStorage.getItem("UuAccount")
		this.UserAccount.name=sessionStorage.getItem("UuName")
	  	this.UserAccount.icon='/static/img/userIcon/'+sessionStorage.getItem("UuImage")
		this.UserAccount.uid= sessionStorage.getItem("UuID")	
	}
  },
  computed:{
  },
  mounted(){
	  this.setData()
  }
}
</script>>