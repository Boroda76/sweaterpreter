<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>

<div>
<form method="post" enctype="multipart/form-data">
<input type="text" name="text" placeholder="type message..." />
<input type="text" name="tag" placeholder="type tag..." />
<input type="file" name="file"> 
<input type="hidden" name="_csrf" value="${_csrf.token}"/>
<button type="submit">Send message</button>
</form>

</div>
<form method="get" action="/main">
<input type="text" name="filter" placeholder="type search term..." value=${filter!} />

<button type="submit">Search</button>
</form>
<div>Messages list</div>

<#list messages as message>
<div>
<b>${message.id}</b>
<span>${message.text}</span>
<i>${message.tag}</i>
<strong>${message.authorName}</strong>
<div>
<#if message.filename??>
<img src="/img/${message.filename}">
</#if>
</div>
</div>
<#else> 
No messages!
</#list> 
</@c.page>