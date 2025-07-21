# WVP Project

- Workshop:
  - PK. id: int
  - name: not null
  - email: string 

- Vehicle:
  - PK. id: int
  - VIN: string
  - brand: string  (enum)
  - model: string (enum)

- Workshops Vehicles:
  - PK. id: int
  - FK (workshop). id_workshop
  - FK (vehicle). id_vehicle

- Part:
  - PK. id: int
  - name: string (enum)
  - part_num: string (enum)
  - brand: string (enum)
  - quantity: int

- Vehicles Part:
  - id: int
  - FK (vehicle) id_vehicle: int
  - FK (part) id_parts: int

- Una parte puede estar en varios vehículos.


## Endpoints (/api/v1)
### Workshop - /workshop
- Add - POST
- View - GET (1 by id) `/workshop/{id}`
  - `/all` (all workshops)
  - `/vehicles` (all vehicles)
- Edit - UPDATE `/workshop/{id}`
- Delete - DELETE `/workshop/{id}`

### Vehicle - /vehicle
- Add - POST
- View - GET (1 by id) `/vehicles/{id}` 
  - `/all`
  - `/parts` (all parts)
  - `/workshop` (ubic)
- Edit - UPDATE `/vehicles/{id}` 
- Delete - DELETE `/vehicles/{id}` 

### Part - /part
- Add - POST
- View - GET (1 by id) `/part/{id}` 
  - `/all`
- Edit - UPDATE `/part/{id}` 
- Delete - DELETE `/part/{id}` 
