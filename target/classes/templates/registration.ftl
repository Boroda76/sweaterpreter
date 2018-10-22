<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
        <div class="mb-1">
        Register new user here!
        	${message!}
        </div>
        
<@l.login "/registration" true/>
</@c.page>