# Question: #


Events are serialized and saved to DB.
After I change the package of an event class, I get
xstream.mapper.CannotResolveClassException

How will we migrate our existing data (serialized events) in such a case?

generally, you use Upcasters to convert "old" serialized events to the new format. There is a small section about that in the reference manual: http://www.axonframework.org/docs/1.2/repositories-and-event-stores.html#d4e662

Alternatively, if you use XStream, you can add an alias from the old package name to the new one. To make sure the new events are stored under their own package name, you have to add the alias twice, like so:\

serializer.addPackageAlias("old.package.name", "new.package.name"); // makes sure old package is read in as new.package
serializer.addPackageAlias("new.package.name", "new.package.name"); // makes sure that new.package is also written as new.package

This removes the need for upcasters and performs a lot better too.

Cheers,

Allard