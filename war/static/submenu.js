var menus;
var sliders;

function setUpMenu()
{
	$('menuGlobal').setOpacity(0);
	$('menuGlobal').setStyle('display', 'block');
	menus = $$('.menuItemContainer');
	menus.each(function(item) {
		var maxwidth = 0;
		item.getElement('div.subMenuContainer').getElements('div.subMenuItem').each(function (sItem) {
			maxwidth = (maxwidth < sItem.getElement('a').getWidth()) ? sItem.getElement('a').getWidth() : maxwidth;
		});
		
		maxwidth = (maxwidth < item.getElement('div.menu').getWidth()) ? item.getElement('div.menu').getWidth() : maxwidth;
		
		maxwidth += 7;
		
		item.getElement('div.subMenuContainer').getElement('div.topLine').setStyle('width', (maxwidth + 7) + 'px');
		item.getElement('div.subMenuContainer').getElement('div.bottomLine').setStyle('width', (maxwidth + 7) + 'px');
		item.getElement('div.subMenuContainer').getElement('div.topLine').getElement('.centerColumn').setStyle('width', (maxwidth - 9) + 'px');
		item.getElement('div.subMenuContainer').getElement('div.bottomLine').getElement('.centerColumn').setStyle('width', (maxwidth - 9) + 'px');
		item.getElement('div.subMenuContainer').setStyle('width', item.getElement('div.menu').getWidth() + 'px');
		item.getElement('div.subMenuContainer').getElements('div.subMenuItem').each(function(sItem) {
			sItem.setStyle('width', (maxwidth - 2) + 'px');
		});
		item.getElement('div.subMenuContainer').getElement('div.subMenu').maxHeight = item.getElement('div.subMenuContainer').getElement('div.subMenu').getHeight();
		item.getElement('div.subMenuContainer').getElement('div.subMenu').maxWidth = item.getElement('div.subMenuContainer').getElement('div.subMenu').getWidth();
		
		item.slider = new Fx.Morph(item.getElement('div.subMenuContainer').getElement('div.subMenu'), {
			duration: 300,
			transition: Fx.Transitions.Quart.easeIn,
			link: 'chain'
		}).set({ 'height': 0, 'overflow': 'hidden', 'width': 0, 'opacity': 0 });
		
		item.addEvent('mouseenter', function() {
			this.slider.start({
				'height': [item.getElement('div.subMenuContainer').getElement('div.subMenu').getHeight(), item.getElement('div.subMenuContainer').getElement('div.subMenu').maxHeight],
				'width': [item.getElement('div.subMenuContainer').getElement('div.subMenu').getWidth(), item.getElement('div.subMenuContainer').getElement('div.subMenu').maxWidth],
				'opacity': [item.getElement('div.subMenuContainer').getElement('div.subMenu').getOpacity(), 1]
			});
			this.slider.start({ 'overflow': 'visible' });
			this.getElement('div.menu').getElement('div.menuBGLeft').setStyle('background', 'url(img/menuOverBGLeft.gif) no-repeat');
			this.getElement('div.menu').getElement('div.menuBGRepeat').setStyle('background', 'url(img/menuOverBGRepeat.gif) repeat-x');
			this.getElement('div.menu').getElement('div.menuBGRepeat').setStyle('color', '#D5104B');
			this.getElement('div.menu').getElement('div.menuBGRight').setStyle('background', 'url(img/menuOverBGRight.gif) repeat-x');
			
		});
		item.addEvent('mouseleave', function() {
			this.slider.cancel();
			this.slider.set({
				'height': 0,
				'width': 0,
				'opacity': 0,
				'overflow': 'hidden'
			});
			this.getElement('div.menu').getElement('div.menuBGLeft').setStyle('background', '');
			this.getElement('div.menu').getElement('div.menuBGRepeat').setStyle('background', '');
			this.getElement('div.menu').getElement('div.menuBGRepeat').setStyle('color', '#FFFFFF');
			this.getElement('div.menu').getElement('div.menuBGRight').setStyle('background', '');
		});
	});
	
	$('menuGlobal').setOpacity(1);
}

window.addEvent("domready", setUpMenu);

function listChange(url)
{
	window.location = url;
}