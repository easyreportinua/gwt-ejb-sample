I was looking for an example of EJB/JPA/GWT integration and couldn't find a simple one. This is a port of the DynaTable sample from GWT.
Hibernate is used as the JPA provider.

While GWT 2.0 added the support for JPA annotations, it doesn't handle hibernate specific collections:
**Persistent(Bag/List/Set/Map)** Cglib enchanced objects etc.

The approach to solving this has been taken from the book:
Pro Web 2.0 Application Development with GWT:
**http://apress.com/book/view/9781590599853** source code: http://github.com/jdwyah/tocollege.net

I wish I had found the project earlier, but this one might be a bit simpler.

I have deployed the project on Jboss AS 5.1.0 GA ( it uses @EJB annotations in the servlet and this wasn't supported in some older versions). It might not work on other application servers.

The example uses PostgreSQL with a database named mits with user user:capital pass:capital.