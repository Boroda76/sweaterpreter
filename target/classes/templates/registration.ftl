<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
        <div>
        Register new user here!
        	${message!}
        </div>
        
<@l.login "/registration" />
</@c.page>