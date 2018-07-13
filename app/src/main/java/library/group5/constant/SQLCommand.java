package library.group5.constant;

/**
 * Created by Isha on 05-04-2018.
 */

public abstract class SQLCommand {

    //Ouery for Landing Page
    public static String SELECT_USER_EMAIL = "select User_Email from User";

    //Query to insert new user
    public static String INSERT_USER = "insert into User(User_Name,User_Email,User_Password) values(?,?,?)";

    //Query for event page
    public static String SELECT_EVENT = "select Ev_Name from Event";

    public static String SELECT_EVENT_BY_NAME ="SELECT * FROM Event  WHERE Ev_Name = ?";

    //Select, Add, Delete, Update  Event

    //public static String AddEvent =" INSERT INTO Event (Ev_Name, Ev_Date, User_ID, Ev_Guests) " +
    //"VALUES (?, ?, ?, ?)";

    //public static String DeleteEvent ="DELETE FROM Event WHERE Ev_ID =?";

    public static String UPDATE_EVENT ="UPDATE Event " +
            "SET Ev_Guests = ?, Ev_Date = ? WHERE Ev_ID = ?";

    public static String DELETE_EVENT ="DELETE FROM Event WHERE Ev_ID =?";

    //Select, Add, Delete, Update  Menu Recipes

    public static String SELECT_EVENT_MENU ="SELECT Rec_Name,Ev_Rec_Servings_Req FROM Menu,Recipe " +
            "WHERE Menu.Rec_ID = Recipe.Rec_ID and  Ev_ID = ? ";

    public static String SELECT_RECIPE = "select Rec_Name from Recipe";

    public static String SELECT_RECIPE_BY_NAME ="SELECT * FROM Recipe WHERE Rec_Name = ?";

    public static String ADD_RECIPE_MENU ="INSERT INTO Menu (Rec_ID, Ev_ID, Ev_Rec_Servings_Req) " +
            "VALUES (?, ?, ?)";

    public static String DELETE_RECIPE_FROM_MENU ="DELETE " +
            "FROM Menu " +
            "WHERE Ev_ID =? AND Rec_ID = ?";

    //public static String UpdateMenuRecipeServings ="UPDATE Menu " +
           // "SET Ev_Rec_Servings_Req = ? " +
           // "WHERE Ev_ID = ? AND Rec_ID = ?";

    //Query for list Page

    //Select, Add, Delete Lists

    public static String SELECT_LIST = "select List_Name from List";


    public static String SELECT_LIST_BY_NAME= "SELECT * " +
            "FROM List  " +
            "WHERE List_Name = ?";

    //public static String AddList ="INSERT INTO List (List_Name, User_ID) " +
           // "VALUES (?, ?)";

    public static String DELETE_LIST ="DELETE " +
           "FROM List " +
            "WHERE List_ID=?";

    //Select, Add, Delete Events to/from Lists

    public static String SELECT_EVENT_LIST ="SELECT Ev_Name,Ev_Guests,Ev_Date " +
            "FROM Event " +
            "WHERE Event.List_ID = ?";

    public static String ADD_EVENT_TO_LIST ="UPDATE Event " +
            "SET List_ID = ? " +
            "WHERE EV_ID = ?";

    public static String DELETE_EVENT_FROM_LIST ="UPDATE Event " +
            "SET List_ID = NULL " +
            "WHERE EV_ID = ?";


    //Query for Recipe Page

    //Select, Add, Delete, Update  Recipe

   // public static String AddRecipe ="INSERT INTO Recipe VALUES(?, ?, ?, ?)";

    public static String DELETE_RECIPE ="DELETE FROM Recipe WHERE Rec_ID= ?";

    public static String UPDATE_RECIPE_SERVINGS ="UPDATE Recipe " +
            "SET Rec_Servings_Made = ? " +
             "WHERE Rec_ID = ?";

    //Select, Add, Delete, Update Recipe Ingredients

    public static String SELECT_INGREDIENT ="SELECT Ing_Name FROM Ingredient";

    public static String SELECT_RECIPE_INGREDIENT ="SELECT * FROM Rec_Ingred WHERE Rec_ID = ?";

    public static String SELECT_RECIPE_INGREDIENT_DETAIL ="SELECT Ing_Name,Rec_Ing_Qty,Unit_Name FROM Rec_Ingred,Ingredient,Unit "+
            "WHERE Rec_Ingred.Ing_Id = Ingredient.Ing_Id "+
            "and Rec_Ingred.Unit_Id = Unit.Unit_Id "+
            "and Rec_ID = ?";


    public static String ADD_ING_TO_REC ="INSERT INTO Rec_Ingred(Rec_ID, Ing_ID, Rec_Ing_Qty, Unit_id) " +
            "VALUES (?, ?, ?, ?)";

    public static String UPDATE_REC_ING_QTY_UNIT ="UPDATE Rec_Ingred " +
            "SET Rec_Ing_Qty = ?, Unit_ID = ? " +
            "WHERE Rec_ID = ? AND Ing_ID = ?";

    public static String DEL_ING_FROM_RECIPE ="DELETE " +
            "FROM Rec_Ingred " +
            "WHERE Rec_ID= ? AND Ing_ID = ?";

    //Select Unit in the Recipe Page

    public static String SELECT_UNIT = "select Unit_Name from Unit";

    public static String SELECT_UNIT_BY_NAME ="SELECT * FROM Unit WHERE Unit_Name = ?";

    public static String SELECT_INGREDIENT_BY_NAME ="SELECT Ing_Id FROM Ingredient WHERE Ing_Name = ?";




    public static String CREATE_SHOPPING_LIST ="SELECT  Ing.Ing_Name, " +
            "SUM(ROUND(" +
            "CASE WHEN UP.Unit_Type_ID = UR.Unit_Type_ID " +
            "THEN (ROUND(.4999+CAST(M.Ev_Rec_Servings_Req AS REAL)/CAST(R.Rec_Servings_Made AS REAL),0)*RI.REC_ING_QTY*UR.Unit_Convfactor/UP.Unit_Convfactor) " +
            "WHEN UP.Unit_Type_ID = 1 AND UR.Unit_Type_ID = 3 " +
            "THEN (ROUND(.4999+CAST(M.Ev_Rec_Servings_Req AS REAL)/CAST(R.Rec_Servings_Made AS REAL),0)*(RI.REC_ING_QTY*UR.Unit_Convfactor*Ing.Ing_Density)/Ing.Ing_Unit_Wt) " +
            "WHEN UP.Unit_Type_ID = 1 AND UR.Unit_Type_ID = 3 " +
            "THEN (ROUND(.4999+CAST(M.Ev_Rec_Servings_Req AS REAL)/CAST(R.Rec_Servings_Made AS REAL),0)*(RI.REC_ING_QTY*UR.Unit_Convfactor)/Ing.Ing_Unit_Wt) " +
            "WHEN UP.Unit_Type_ID = 2 AND UR.Unit_Type_ID = 1 " +
            "THEN (ROUND(.4999+CAST(M.Ev_Rec_Servings_Req AS REAL)/CAST(R.Rec_Servings_Made AS REAL),0)*(RI.REC_ING_QTY*Ing.Ing_Unit_Wt/Ing.Ing_Density)/UP.Unit_Convfactor) " +
            "WHEN UP.Unit_Type_ID = 2 AND UR.Unit_Type_ID = 2 " +
            "THEN (ROUND(.4999+CAST(M.Ev_Rec_Servings_Req AS REAL)/CAST(R.Rec_Servings_Made AS REAL),0)*(RI.REC_ING_QTY*UR.Unit_Convfactor/Ing.Ing_Density)/UP.Unit_Convfactor) " +
            "WHEN UP.Unit_Type_ID = 3 AND UR.Unit_Type_ID = 1 " +
            "THEN (ROUND(.4999+CAST(M.Ev_Rec_Servings_Req AS REAL)/CAST(R.Rec_Servings_Made AS REAL),0)*RI.REC_ING_QTY*Ing.Ing_Unit_Wt/UP.Unit_Convfactor) " +
            "WHEN UP.Unit_Type_ID = 3 AND UR.Unit_Type_ID = 2 " +
            "THEN (ROUND(.4999+CAST(M.Ev_Rec_Servings_Req AS REAL)/CAST(R.Rec_Servings_Made AS REAL),0)*(RI.REC_ING_QTY*UR.Unit_Convfactor*Ing.Ing_Density)/UP.Unit_Convfactor) " +
            "ELSE \"Bad Code\" " +
            "END,2)) AS QtyReqd, UP.Unit_Abbrev AS PurchaseUnits " +
            "FROM Ingredient AS Ing " +
            "JOIN Unit       AS UP ON UP.Unit_ID = Ing.Ing_Purchase_Unit_ID " +
            "JOIN Rec_Ingred AS RI ON Ing.Ing_ID = RI.Ing_ID " +
            "JOIN Unit       AS UR ON RI.Unit_ID = UR.Unit_ID " +
            "JOIN Recipe     AS R  ON RI.Rec_ID =R.Rec_ID " +
            "JOIN Menu       AS M  ON R.Rec_ID = M.Rec_ID " +
            "JOIN Event      AS E  ON M.Ev_ID = E.Ev_ID " +
            "JOIN List       AS L  ON E.List_ID = L.List_ID " +
            "WHERE L.List_ID = ? " +
            "GROUP BY Ing.Ing_ID " +
            "ORDER BY Ing.Cat_ID ASC";


}
